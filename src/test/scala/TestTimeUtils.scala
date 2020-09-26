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
    // a bit modify DatetimeUtils utils tests, so it will not care about time zone 
    assert(time1.toString().startsWith("2005-07-30"))
    assert(time2.toString().startsWith("2005-07-01"))
    assert(time3.toString().startsWith("2005-07-30"))
    assert(time4.toString().startsWith("2005-07-30"))
  }
}