package AsyncProducerConsumer

import java.util.Properties

import ProducerConsumerPartitioner.Producer.{producer, props, topic}
import org.apache.kafka.clients.producer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

// UDF
import ProducerUtils.AsyncSyncProducer

object ProducerRunner extends App{

  val props = new Properties()
  val topic = "async_topic"
  val brokerList = "localhost:9092"

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

  val sync:Boolean = false

  val producer = AsyncSyncProducer.apply(topic, brokerList, sync)

  try{
    for (i <- 2 to 20){
      //val record = new ProducerRecord[String, String](topic,"IT_" + i,"My Site is yen.com " + i)
      val record:String = s"hola ! this is my $i msg !!!"
      println(record)
      producer.send(record)
    }
  } catch {
    case e:Exception => e.printStackTrace()
  } finally {
    println("Aync producer sending completed, plz check if the job success or not")
  }
}