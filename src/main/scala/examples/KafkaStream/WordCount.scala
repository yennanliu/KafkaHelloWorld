package KafkaStream

// https://supergloo.com/kafka-streams/kafka-streams-scala-example/
// https://github.com/tmcgrath/kafka-streams/blob/master/src/main/scala/com/supergloo/WordCount.scala

import java.time.Duration
import java.util.Properties

import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

object WordCount extends App {
  println("run WordCount ...")

  import Serdes._

  val outputTopic = "word_count_results"
  val applicationIdConfig = "wordcount-modified"
  val bootstrapServer = "localhost:9092"

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationIdConfig)
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String]("text_lines")
  
  println("write wordcout result to topic : word_count_results ...")

  val wordCounts: KTable[String, Long] = textLines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
    .count()
  wordCounts.toStream.to(outputTopic)

  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
  streams.start()

  sys.ShutdownHookThread {
     streams.close(Duration.ofSeconds(10))
  }
}