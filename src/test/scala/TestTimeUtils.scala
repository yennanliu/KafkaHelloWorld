package KafkaHelloWorld

import org.scalatest.funsuite.AnyFunSuite
// UDF
import common.DatetimeUtils._

class TestTimeUtils extends AnyFunSuite {

  test("time format should equal as below via TimeUtils") {
    val time1 = DateFormat.parseDateTime("2005-07-30")
    val time2 = yearMonthFormat.parseDateTime("2005-07")
    val time3 = DateHourFormat.parseDateTime("2005-07-30 11")
    val time4 = DateHourMinSecFormat.parseDateTime("2005-07-30 11:03:04")

    //val DateHourMinSecFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    assert(time1.toString() == "2005-07-30T00:00:00.000+08:00")
    assert(time2.toString() == "2005-07-01T00:00:00.000+08:00")
    assert(time3.toString() == "2005-07-30T11:00:00.000+08:00")
    assert(time4.toString() == "2005-07-30T11:03:04.000+08:00")
  }
}