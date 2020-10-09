//package SchemaRegistryFlink
//
//// https://github.com/jfrazee/schema-registry-examples/blob/master/src/main/scala/io/atomicfinch/examples/flink/SchemaRegistryExample.scala
//
//import scala.util.Try
//import org.apache.avro.generic.GenericRecord
//
//import org.apache.flink.streaming.api.scala._
//import org.apache.flink.api.java.utils.ParameterTool
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
//import org.apache.flink.streaming.util.serialization.SimpleStringSchema
//
//// run --bootstrap.servers localhost:9092 --zookeeper.connect --group.id test --schema NONE --topic test
//object SchemaRegistryExample {
//
//  def main(args: Array[String]): Unit = {
//    val params = ParameterTool.fromArgs(args)
//
//    if (params.getNumberOfParameters < 4) {
//      println("Missing parameters!\n"
//        + "Usage: SchemaRegistryExample "
//        + "--topic <topic> "
//        + "--bootstrap.servers <kafka brokers> "
//        + "--zookeeper.connect <zk quorum> "
//        + "--group.id <some id> "
//        + "[--schema <type>] "
//        + "[--output <path>]")
//      return
//    }
//
//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//
//    env.getConfig.setGlobalJobParameters(params)
//
//    val stream =
//      params.get("schema", "NONE").toUpperCase match {
//        case "NONE" => {
//          val consumer =
//            new FlinkKafkaConsumer010(
//              params.getRequired("topic"),
//              new SimpleStringSchema,
//              params.getProperties)
//          consumer.setStartFromLatest()
//          env.addSource(consumer)
//        }
//        case "EMBEDDED" => {
//          val consumer =
//            new FlinkKafkaConsumer010(
//              params.getRequired("topic"),
//              new EmbeddedAvroDeserializationSchema[GenericRecord](classOf[GenericRecord]),
//              params.getProperties)
//          consumer.setStartFromLatest()
//          env.addSource(consumer).map(_.toString)
//        }
//        case "CONFLUENT" => {
//          val consumer =
//            new FlinkKafkaConsumer010(
//              params.getRequired("topic"),
//              new ConfluentRegistryDeserializationSchema[GenericRecord](classOf[GenericRecord]),
//              params.getProperties)
//          consumer.setStartFromLatest()
//          env.addSource(consumer).flatMap { record =>
//            for (user <- Try(record.get("user").asInstanceOf[GenericRecord]).toOption)
//              yield Seq(
//                Option(user.get("name")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("screen_name")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("description")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("location")).map(_.toString.replaceAll("\\s", " ")).getOrElse("")).mkString("\t")
//          }
//        }
//        case "HORTONWORKS" => {
//          val consumer =
//            new FlinkKafkaConsumer010(
//              params.getRequired("topic"),
//              new HortonworksRegistryDeserializationSchema[GenericRecord](classOf[GenericRecord]),
//              params.getProperties)
//          consumer.setStartFromLatest()
//          env.addSource(consumer).flatMap { record =>
//            for (user <- Try(record.get("user").asInstanceOf[GenericRecord]).toOption)
//              yield Seq(
//                Option(user.get("name")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("screen_name")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("description")).map(_.toString.replaceAll("\\s", " ")).getOrElse(""),
//                Option(user.get("location")).map(_.toString.replaceAll("\\s", " ")).getOrElse("")).mkString("\t")
//          }
//        }
//        case s => {
//          println(s"Unknown schema type: ${s}")
//          return
//        }
//      }
//
//    if (params.has("output")) {
//      stream.writeAsText(params.get("output"))
//    } else {
//      println("Printing result to stdout. Use --output to specify output path.")
//      stream.print()
//    }
//
//    env.execute("SchemaRegistryExample")
//
//    ()
//  }
//
//}