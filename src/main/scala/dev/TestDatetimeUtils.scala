package dev

//UDF
import common.DatetimeUtils._

object TestDatetimeUtils extends App{
  println("run TestDatetimeUtils ...")
  println(s"now : $now")
  println(s"nowLong : $nowLong")

  val time1 = DateFormat.parseDateTime("2005-07-30")
  val time2 = yearMonthFormat.parseDateTime("2005-07")
  val time3 = DateHourFormat.parseDateTime("2005-07-30 11")

  println(s"time1 : $time1")
  println(s"time2 : $time2")
  println(s"time3 : $time3")
}
