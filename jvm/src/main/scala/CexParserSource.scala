package edu.holycross.shot.cex

import scala.io.Source

object CexParserSource {

  def fromFile(fileName: String): CexParser = {
    val lns = Source.fromFile(fileName).getLines.toVector
    CexParser(lns.mkString("\n"))
  }
}
