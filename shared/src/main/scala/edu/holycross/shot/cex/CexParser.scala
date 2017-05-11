package edu.holycross.shot.cex

import scala.scalajs.js
import js.annotation.JSExport
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map

/** A CEX parser making blocks of a CEX String available
* as a named map of label -> vectors of data.
*
* @param rawCex CEX data to parse.
*/
@JSExport case class CexParser (rawCex: String) {


  /** Find optional string value for cex version
  * reported for this source.
  */
  def version: Option[String] = {
    block("cexversion").size match {
      case 0 => None
      case 1 =>   Some(    block("cexversion")(0))
      case n: Int => throw new Exception("Only one cexversion block allowed: found " + n)
    }
  }

  /** Find a string value for the cex version
  * reported for this source, or a null string if None.
  */
  def versionString : String = {
    block("cexversion").size match {
      case 0 => ""
      case 1 => block("cexversion")(0)
      case n: Int =>  throw new Exception("Only one cexversion block allowed: found " + n)
    }
  }


  /** Vector of labelled blocks of CEX strings. */
  def rawBlocks = rawCex.split("#!").filter(_.nonEmpty).toVector

  /** Each block of data as a vector of non-empty, non-comment lines. */
  def blocksContent: Vector[Vector[String]] = {
    //Vector[String]()
    val content = for (b <- rawBlocks if b.split("\n").size > 1) yield {
      val lns = b.split("\n").toVector
      lns.filterNot(_.startsWith("#"))
    }
    content
  }



  /** Map of block labels to one or more data sets. */
  def blockMap : scala.collection.immutable.Map[String,Vector[String]] = {
    val blocksToContent = scala.collection.mutable.Map[String, Vector[String]]()

    for (lns <- blocksContent if lns.size > 1) {
      if (blocksToContent.keySet.contains(lns(0))) {
        val v = blocksToContent(lns(0)) :+ lns.drop(1).mkString("\n")

      } else {
        val s = lns.drop(1).mkString("\n")
        val v = Vector( s)
        blocksToContent += (lns(0) -> v)
      }
    }
    blocksToContent.toMap
  }

  /** Set of block labels in this CEX library
  */
  val blocks: Set[String] = {
    blockMap.keySet
  }


  /** Find content for block label.
  */
  def block(blockLabel: String) : Vector[String] = {
    if (blocks.contains(blockLabel)) {
      blockMap(blockLabel)
    } else {
      Vector[String]()
    }
  }

  assert ( labels.union(blocks) == labels, "Inavlid block label in " + blocks)

}
