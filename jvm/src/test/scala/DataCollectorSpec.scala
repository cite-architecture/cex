package edu.holycross.shot.cex
import org.scalatest.FlatSpec
import scala.xml._
import java.io.File
//import edu.holycross.shot.cex._

class DataCollectorSpec extends FlatSpec {

  "The DataCollector object" should "list files in a directory" in  {
    val srcDir = "jvm/src/test/resources/cex"
    val xmlFiles = DataCollector.filesInDir(srcDir, "cex")
    val expected = Vector(
      new File("jvm/src/test/resources/cex/_lib.cex"),
      new File("jvm/src/test/resources/cex/texts.cex")
    )
    assert(xmlFiles == expected)
  }


  it should "collect text content from CEX files" in {
    val srcDir = "jvm/src/test/resources/cex"
    val cexString = DataCollector.compositeFiles(srcDir, "cex")
    val cex = CexParser(cexString)

    val expectedLabels = Set("ctscatalog", "ctsdata", "cexversion", "citelibrary")
    assert(cex.blockLabels == expectedLabels)
  }


  it should "drop headers from collected files as specified" in {
    val srcDir = "jvm/src/test/resources/withHeaders"
    val dataPlusHeader = DataCollector.compositeFiles(srcDir, "cex")
    assert(dataPlusHeader.split("\n").size == 3)
    val dataOnly  = DataCollector.compositeFiles(srcDir, "cex", 1)
    assert(dataOnly.split("\n").size == 2)
  }
}
