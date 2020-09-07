package Producer

// https://ithelp.ithome.com.tw/articles/10188761

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.{Date, Properties}

// ProducerRecord is the new API of KeyedMessage
// https://stackoverflow.com/questions/35984247/what-is-the-difference-between-kafka-producerrecord-and-keyedmessage
import org.apache.kafka.clients.producer.{ProducerRecord, Producer, ProducerConfig}

import org.apache.kafka.clients.producer.KafkaProducer

import scala.util.Random

object KafkaProducerApp2 extends App{

    val topic = "test_topic"
    val brokers = "localhost:9092"
    val rnd = new Random()

    val props = new Properties()
    props.put("bootstrap.servers", brokers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("acks","all")

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