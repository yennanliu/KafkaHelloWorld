//package Producer
//
// https://github.com/jfrazee/schema-registry-examples/blob/master/src/main/scala/io/atomicfinch/examples/flink/SchemaRegistryExample.scala
//import scala.collection.JavaConverters._
////import com.company.Schemas.Block
//import org.apache.kafka.common.serialization.Serdes
//import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
//
//import com.sksamuel.avro4s.{FromRecord, RecordFormat, ToRecord}
//import io.confluent.kafka.streams.serdes.avro.GenericAvroSerializer
//import io.confluent.kafka.streams.serdes.avro.GenericAvroDeserializer
//
//import org.apache.avro.generic.GenericRecord
//import org.apache.kafka.common.serialization.Serdes
//import org.apache.kafka.streams.scala.ImplicitConversions._
//import org.apache.kafka.streams.scala._
//import org.apache.kafka.streams.scala.kstream.KStream
//
//import java.util.{Date, Properties}
//import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
//import scala.util.Random
//
//import com.typesafe.scalalogging.LazyLogging
//import  io.confluent.kafka.schemaregistry._
//
//object ProducerSchema extends App with LazyLogging {
//
//  val config = new Properties()
//  config.put(StreamsConfig.APPLICATION_ID_CONFIG, "application_id")
//  config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
//
//  av
//
//  implicit val url: avro.SchemaRegistryUrl = "http://localhost:8081"
//
//  val builder = new StreamsBuilder()
//  val blocks  = Avro.stream[Block](builder, "blocks")
//
//  (new KafkaStreams(builder.build(), config)).start()
//
//  sys.ShutdownHookThread {
//    streams.close(10, TimeUnit.SECONDS)
//  }
//}
//
//
//
