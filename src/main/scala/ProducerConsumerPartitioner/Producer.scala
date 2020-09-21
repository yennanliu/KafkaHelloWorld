package ProducerConsumerPartitioner 

import java.util.Properties
import org.apache.kafka.clients.producer._

object Producer extends App {

  val props = new Properties()
  val topic = "test_topic_2"
  
  props.put("bootstrap.servers", "localhost:9092,localhost:9093")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("partitioner.class", "Partitioner.CustomPartitioner1")

  val producer = new KafkaProducer[String, String](props)

  try {
    for (i <- 2 to 10) {
      val record = new ProducerRecord[String, String](topic,"IT" + i,"My Site is yen.com " + i)
      println(record)
      producer.send(record)
    }

    for (i <- 2 to 10) {
      val record = new ProducerRecord[String, String](topic,"COMP" + i,"My Site is yen.com " + i)
      println(record)
      producer.send(record)
    }

  } catch {
    case e: Exception => e.printStackTrace()
  } finally {
    producer.close()
  }
}