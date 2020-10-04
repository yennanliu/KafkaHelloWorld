package Partitioner

import java.util
import org.apache.kafka.common.record.InvalidRecordException
import org.apache.kafka.common.utils.Utils
import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import scala.util.Random

class RandomPartitioner extends Partitioner {

    println("run RandomPartitioner ...")

    override def configure(configs: util.Map[String, _]): Unit = {}
    override def partition(topic: String,key: Any, keyBytes: Array[Byte], value: Any,valueBytes: Array[Byte],cluster: Cluster): Int = {
    // get partition via Scala random number
    val randomInt = Random.nextInt(5)  // will return random integer between 1 and 5
    println(s"topic = $topic, ke = $key, keyBytes = $keyBytes, valueBytes = $valueBytes, cluster = $cluster")
    randomInt
  }
  override def close(): Unit = {}
}