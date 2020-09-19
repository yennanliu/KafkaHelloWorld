package SimpleProducerConsumer 

import java.util.{Collections, Properties}
import java.util.concurrent._
//import kafka.consumer.{Consumer, ConsumerConfig, KafkaStream}
import kafka.utils.Logging
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.streams.KafkaStreams
import scala.collection.JavaConversions._

object Consumer extends App {
  val topic = "simple_topic"
  val example = new ConsumerApp("localhost:9092", "group1", topic)
  example.run()
}

class ConsumerApp(val zookeeper: String,
               val groupId: String,
               val topic: String) extends Logging {

  val config = createConsumerConfig(zookeeper, groupId)
  val consumer = new KafkaConsumer[String, String](config)
  var executor: ExecutorService = null

  def shutdown() = {
    if (consumer != null)
      consumer.close()
    if (executor != null)
      executor.shutdown()
  }
  
  def createConsumerConfig(zookeeper: String, groupId: String): Properties = {
      val props = new Properties()
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, zookeeper)
      props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
      props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
      props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
      props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000")
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
      props
  }

  def run() = {
    consumer.subscribe(Collections.singletonList(this.topic))

    Executors.newSingleThreadExecutor.execute(    new Runnable {
      override def run(): Unit = {
        while (true) {
          val records = consumer.poll(1000)
          for (record <- records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
          }
        }
      }
    })
  }
}
