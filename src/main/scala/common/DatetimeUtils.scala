package common

import org.joda.time.{ DateTime, DateTimeZone, Days }
import org.joda.time.format.{ DateTimeFormat, DateTimeFormatter, ISODateTimeFormat }

object DatetimeUtils {
   val DateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
   val yearMonthFormat = DateTimeFormat.forPattern("yyyy-MM")
   val DateHourFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH")
   val DateHourMinSecFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

   val ISO8601UTCFormat = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZoneUTC()

   // get current time in Millis
   def now(): DateTime = new DateTime(System.currentTimeMillis())

   def nowLong(): Long = System.currentTimeMillis()

}