package ProducerConsumerPartitioner 

import java.util
import org.apache.kafka.common.record.InvalidRecordException
import org.apache.kafka.common.utils.Utils
import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

class Partitioner_ extends Partitioner {

  val departmentName = "IT"
  override def configure(configs: util.Map[String, _]): Unit = {}
  override def partition(topic: String,key: Any, keyBytes: Array[Byte], value: Any,valueBytes: Array[Byte],cluster: Cluster): Int = {

    // hardcode here, need to fix
    val partitions = List("abc", "def", "ghi", "jkl", "mno")
    //val partitions = cluster.partitionsForTopic(topic)
    val numPartitions = partitions.size

    println(s"*** partitions : $partitions")
    println(s"*** numPartitions : $numPartitions")

    val it = Math.abs(numPartitions * 0.4).asInstanceOf[Int]

    if ((keyBytes == null) || (!key.isInstanceOf[String]))

      throw new InvalidRecordException("All messages must have department name as key")

    if (key.asInstanceOf[String].startsWith(departmentName)) {
      val p = Utils.toPositive(Utils.murmur2(keyBytes)) % it
         p
    } else {
      val p = Utils.toPositive(Utils.murmur2(keyBytes)) % (numPartitions - it) + it
           p
    }
  }
  override def close(): Unit = {}
}