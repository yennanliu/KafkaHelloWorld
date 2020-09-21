package SimpleProducerConsumer

// https://ithelp.ithome.com.tw/articles/10188761
// ProducerRecord is the new API of KeyedMessage
// https://stackoverflow.com/questions/35984247/what-is-the-difference-between-kafka-producerrecord-and-keyedmessage

import org.apache.kafka.clients.producer.{ProducerRecord, Producer, ProducerConfig, KafkaProducer}
import java.util.{Date, Properties}
import scala.util.Random

object Producer extends App{

  val topic = "test_topic"
  val brokers = "localhost:9092"
  val rnd = new Random()

  val props = new Properties()
  props.put("bootstrap.servers", brokers)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  //  acks could = 0, 1, or all : https://docs.confluent.io/current/installation/configuration/producer-configs.html
  props.put("acks","all")

  println(s"*** running producer, topic = $topic")
  val producer = new KafkaProducer[String, String](props)

  for (nEvents <- Range(0, 1000)){
    val runtime = new Date().getTime()
    val ip = "192.168.2." + rnd.nextInt(255)
    val msg = runtime + "," + nEvents + ",www.example.com," + ip
    val data = new ProducerRecord[String, String](topic, ip, msg)
    println("sending data to kafka : " + data)
    producer.send(data)

  }
  producer.close()
}