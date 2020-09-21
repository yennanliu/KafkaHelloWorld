package ProducerConsumerPartitioner 

import java.util.Properties
import org.apache.kafka.clients.producer._

object Producer extends App {

  val props = new Properties()
  val topic = "test_topic_2"
  
  props.put("bootstrap.servers", "localhost:9092,localhost:9093")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("ProducerConsumerPartitioner.class", "Partitioner.Partitioner_")

  val producer = new KafkaProducer[String, String](props)

  try {
    for (i <- 2 to 20) {
      val record = new ProducerRecord[String, String](topic,"IT_" + i,"My Site is yen.com " + i)
      println(record)
      producer.send(record)
    }

    for (i <- 2 to 20) {
      val record = new ProducerRecord[String, String](topic,"COMP_" + i,"My Site is yen.com " + i)
      println(record)
      producer.send(record)
    }

  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    producer.close()
  }
}