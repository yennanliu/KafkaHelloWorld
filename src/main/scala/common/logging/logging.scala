package common.logging

import org.json4s._
import org.json4s.native.Serialization.write
import org.slf4j.LoggerFactory

package object logging {

  private val LOG = LoggerFactory.getLogger(this.getClass)

  implicit val formats = DefaultFormats

  def update[T](value: T): Unit = {
    value match{
      case x: (String, Any) => {
        val v = value.asInstanceOf[(String, Any)]
        LOG.info(s"${v._1} : ${v._2}")
      }
      case x: Map[String, String] => {
        val v = value.asInstanceOf[Map[String, String]]
        LOG.info(v.toString)
      }
    }
  }

  def post(value: (String, String)): Unit = {
    LOG.info(s"${value._1} : ${value._2}")
  }

}
