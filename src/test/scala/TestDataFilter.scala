import org.scalatest.funsuite.AnyFunSuite

import common.DataFilter._

class TestDataFilter extends AnyFunSuite{

  val NeedTrimData = " abc123 def "
  val DigitData = "abc 123"
  val AlphaData = "abc 123"

  test("data should equal below after filtering"){
    assert("abc123 def" == TrimmingString(NeedTrimData))
    assert("123" == GetDigit(DigitData))
    assert("abc " == GetAlpha(AlphaData))
  }
}
