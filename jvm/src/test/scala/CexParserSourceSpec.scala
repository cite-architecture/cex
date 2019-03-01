package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class CexParserSourceSpec extends FlatSpec {
  "A CexParserSource" should "parse CEX in a local file" in {
    val oldexample = "jvm/src/test/resources/copy-paste.cex"
    val cex = CexParserSource.fromFile(oldexample)

    val ex2 = "jvm/src/test/resources/p12r.cex"
    val cex2 = CexParserSource.fromFile(ex2)

    val imgs = "jvm/src/test/resources/testVAimages.cex"
    val imgcex = CexParserSource.fromFile(imgs)
  }
}
