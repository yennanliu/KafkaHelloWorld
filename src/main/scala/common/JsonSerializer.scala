package common

import java.util
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}
//import play.api.libs.json.{Json, Writes}

class JsonSerializer {
  println("run JsonSerializer...")
  //  private val stringSerializer = new StringSerializer
  //  override def configure(configs: util.Map[String, _], isKey: Boolean) =
  //    stringSerializer.configure(configs, isKey)
  //  override def serialize(topic: String, data: A) =
  //    stringSerializer.serialize(topic, Json.stringify(Json.toJson(data)))
  //  override def close() = stringSerializer.close()
}
