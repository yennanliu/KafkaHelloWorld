package ProducerUtils

import java.util.Properties
import org.apache.kafka.clients.producer.{Callback, RecordMetadata, ProducerRecord, KafkaProducer}
import scala.concurrent.Promise

// https://gist.github.com/ryandavidhartman/2285de4bd13a11d8d2e8

case class AsyncSyncProducer(topic: String, brokerList:String, sync: Boolean){
  val props = new Properties()

  //props.put("bootstrap.servers", "localhost:9092,localhost:9093")
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  // acks = 0, 1, or all 
  // https://medium.com/better-programming/kafka-acks-explained-c0515b3b707e
  props.put("acks", "1")
  props.put("produer.type", if(sync) "sync" else "async")

  // how many times to retry when produce request fails?
  props.put("retries", "3")
  props.put("linger.ms", "5")

  // connect to kafka
  val producer = new KafkaProducer[String, String](props)

  def send(value: String): Unit = {
    if (sync) sendSync(value) else sendAsync(value)
  }

  // sync sending
  def sendSync(value: String): Unit = {
    println("sendSync ...")
    val record = new ProducerRecord[String, String](topic, value)
    try{
      producer.send(record).get()
    } catch {
      case e: Exception  =>
        println("send msg failed")
        print(s"e.printStackTrace : ${e.printStackTrace}")
        e.printStackTrace
        System.exit(1)
    }
  }

  // async sending
  def sendAsync(value: String): Unit = {
    println("sendAsync ...")
    val record = new ProducerRecord[String, String](topic, value)
    val p = Promise[(RecordMetadata, Exception)]()
    producer.send(record, new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        p.success((metadata, exception))
        println(s"send Async msg success, p = $p")
      }
    })
    def close():Unit = producer.close()
  }
}