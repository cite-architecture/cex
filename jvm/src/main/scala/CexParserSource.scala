package edu.holycross.shot.cex

import scala.io.Source


import wvlet.log._
import wvlet.log.LogFormatter.SourceCodeLogFormatter


/** Factory for creating [[CexParser]]s for CEX data
* accessible from JVM-specific sources.
*/
object CexParserSource extends LogSupport {


  /** Create a [[CexParser]] for data in a file
  * in the local file system.
  *
  * @param fileName Name of file with CEX source data.
  */
  def fromFile(fileName: String): CexParser = {
    val lns = Source.fromFile(fileName).getLines.toVector
    CexParser(lns.mkString("\n"))
  }
}
