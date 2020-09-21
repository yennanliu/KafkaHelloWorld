package ProducerConsumerPartitioner 

import java.util
import org.apache.kafka.common.record.InvalidRecordException
import org.apache.kafka.common.utils.Utils
import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

class Partitioner_ extends Partitioner {

  override def configure(configs: util.Map[String, _]): Unit = {}
  override def partition(topic: String, key: Any, keyBytes: Array[Byte], value: Any, valueBytes: Array[Byte], cluster: Cluster): Int = {

    // config
    val departmentName = "IT"
    // hardcode here, need to fix
    val partitions = List("IT_123", "IT_456", "IT_789", "abc", "def", "ghi", "jkl")
    //val partitions = cluster.partitionsForTopic(topic)
    val numPartitions = partitions.size
    //val keyBytes = Array[Byte](192.toByte, 168.toByte, 1, 9)

    println(s"*** key : $key")
    println(s"*** keyBytes : $keyBytes")
    println(s"*** value : $value")
    println(s"*** partitions : $partitions")
    println(s"*** numPartitions : $numPartitions")

    val it = Math.abs(numPartitions * 0.4).asInstanceOf[Int]

    if ((keyBytes == null) || (!key.isInstanceOf[String]))
      throw new InvalidRecordException("All messages must have department name as key")

    val r = scala.util.Random
    val p = r.nextInt(10)
    println(s"*** p = $p")
    p
  }
  override def close(): Unit = {}
}