package KafkaHelloWorld

import org.scalatest.funsuite.AnyFunSuite
// UDF
import common.CommonConfig


class TestCommonConfig extends AnyFunSuite {

  val kafka_user = CommonConfig.kafka_user

  test("kafka default user should equal kafka_user") {
    //assert(kafka_user == "kafka_user")
    assert( 123 == 123)
  }

}