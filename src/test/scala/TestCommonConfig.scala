package KafkaHelloWorld

import org.scalatest.funsuite.AnyFunSuite
// UDF
import common.CommonConfig


class TestCommonConfig extends AnyFunSuite {

  val kafka_user = CommonConfig.kafka_user
  val kafka_topic = CommonConfig.kafka_topic
  val kafka_consumer = CommonConfig.kafka_consumer

  test("kafka default config should as the one in application.conf") {
    assert(kafka_user == "kafka_user")
    assert(kafka_topic == "kafka_topic")
    assert(kafka_consumer == "kafka_consumer")
  }
}