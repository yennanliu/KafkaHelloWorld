package Consumer

import java.util.concurrent._
import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.TopicPartition

import scala.collection.JavaConversions._

object KafkaConsumerApp4 {

}

class KafkaConsumerApp4(val brokers: String,
                  val groupId: String,
                  val topic: String) {

  val props = createConsumerConfig()
  println("--> props = " + props)

  // TODO : fix this
  val JAASFile = "kafka_client_jaas.conf"
  val krb5File = "krb5.conf"

  System.setProperty("java.security.auth.login.config",JAASFile)
  System.setProperty("java.security.krb5.conf",krb5File)
  System.setProperty("hadoop.security.authentication", "Kerberos")

  val LOG_DIR = "/logfiles"

  val consumer = new KafkaConsumer[String, String](props)
  var executor: ExecutorService = null

  def shutdown() = {
    if (consumer != null)
      consumer.close()
    if (executor != null)
      executor.shutdown()
  }

  def createConsumerConfig(): Properties = {
    val props = new Properties()
    props.setProperty("topic", "someTopic")
    props.setProperty("bootstrap.servers","host:9092")
    props.setProperty("group.id","someGroup")
    props.setProperty("security.protocol","SASL_PLAINTEXT")
    props.setProperty("sasl.mechanism","GSSAPI")
    props.setProperty("sasl.kerberos.service.name","kerberos_name")
    props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")

    props
  }

  def runWriteOffset():Unit = {
    consumer.subscribe(Collections.singletonList(this.topic))

    // get partitions info
    val partitionInforms = consumer.partitionsFor(topic)

    // make partitions info to set
    val topicPartitionSet = partitionInforms.toList.map{
      partitionInfo => new TopicPartition(topic,partitionInfo.partition()) // partition
    }.toSet

    // get latest commit per partition and saved to Map
    val lastCommittedMap = consumer.committed(topicPartitionSet)

    // get "partition:offset" as res
    val res = lastCommittedMap.toMap.map{
      committedMap => {
        val offset = committedMap._2.offset()
        val partition = committedMap._1.partition()
        s"$partition:$offset"
      }
    }

    // write res ("partition:offset") to offset file
    val curEpochSecond = java.time.Instant.now.getEpochSecond
    println(">>>>> curEpochSecond = " + curEpochSecond)
    // run every 100 sec
    if (curEpochSecond % 100 == 0) {
      // write to partition-<curEpochSecond>.txt
      val PARTITION_BACKUP_FILE = LOG_DIR + s"/partition-$curEpochSecond.txt" //Paths.get(LOG_DIR + s"/partition-$curEpochSecond.txt")
      // write to partition.txt
      val PARTITION_FILE = LOG_DIR + s"/partition.txt"
//       FileUtils.writeFile(res, PARTITION_BACKUP_FILE)
//       FileUtils.writeFile(res, PARTITION_FILE)
    }
  }

  def run() = {
    consumer.subscribe(Collections.singletonList(this.topic))
    Executors.newSingleThreadExecutor.execute( new Runnable {
      override def run(): Unit = {
        while (true) {
          val records = consumer.poll(1000)

          for (record <- records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
           }
          }
        }
      }
    )
  }

}
