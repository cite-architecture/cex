package edu.holycross.shot.cex
import org.scalatest.FlatSpec




class Cex_1_2_ParserSourceSpec extends FlatSpec {

  "A CexParserSource" should "create a CexParser for cex data in a file" in {
    val f = "jvm/src/test/resources/p12r.cex"
    val cex = CexParserSource.fromFile(f)
    cex match {
      case cparser : CexParser => assert(true)
      case _ => fail("should have created a CexParser")
    }
  }


}
