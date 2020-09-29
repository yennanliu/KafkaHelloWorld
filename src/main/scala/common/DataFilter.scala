package common

// filter event data for Kafaka processing

object DataFilter{

  def TrimmingString(data:String):String = data.trim

  def GetDigit(data:String):String = data.filter(_.isDigit)

  def GetAlpha(data:String):String = data.filter(!_.isDigit)
}
