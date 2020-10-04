package AsyncProducerConsumer

import java.util.{Collections, Properties}
import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

object ConsumerRunner extends App {

  val topic = "async_topic"
  val group_id = "group1"
  val brokers = "localhost:9092"

  println(s"*** running Consumer, topic = $topic, group_id = $group_id")

  val props:Properties = new Properties()
  props.put("group.id", group_id)
  props.put("bootstrap.servers", brokers)
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  val consumer = new KafkaConsumer(props)
  val topics = List(topic)
  println(s"*** topics : $topics")
  try {
    consumer.subscribe(topics.asJava)
    println(s"${consumer.subscribe(topics.asJava)}")
    while (true) {
      val records = consumer.poll(10)
      //println(s"records : $records")
      for (record <- records.asScala) {
        println("Topic: " + record.topic() +
          ",Key: " + record.key() +
          ",Value: " + record.value() +
          ", Offset: " + record.offset() +
          ", Partition: " + record.partition())
      }
    }
  }catch{
    case e:Exception => e.printStackTrace()
      println(s"exception : ${e.printStackTrace()}")
  }finally {
    consumer.close()
  }
}