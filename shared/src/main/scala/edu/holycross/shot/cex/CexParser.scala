package edu.holycross.shot.cex

import scala.scalajs.js
import js.annotation.JSExport


/** A CEX parser making blocks of a CEX String available
* as a named map of label -> data.
*
* @param rawCex CEX data to parse.
*/
@JSExport case class CexParser (rawCex: String) {

  /** Array of labelled blocks of CEX strings. */
  val rawBlocks = rawCex.split("#!")

  /** Each block of data as an array of non-empty, non-comment lines. */
  val tidyBlocks = for (b <- rawBlocks if b.split("\n").size > 1) yield {
    val lns = b.split("\n")
    lns.filter(_.nonEmpty).filterNot(_.startsWith("#"))
  }

  /** Map of block labels to data. */
  def blocks: Map[String,String] = {
    val tupleArray = for (lns <- tidyBlocks if lns.size > 1) yield {
      (lns(0) -> lns.drop(1).mkString("\n"))
    }
    tupleArray.toMap
  }

}
