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
    blockVector("cexversion").size match {
      case 0 => None
      case 1 =>   Some(    blockVector("cexversion")(0))
      case n: Int => throw new Exception("Only one cexversion block allowed: found " + n)
    }
  }

  /** Find a string value for the cex version
  * reported for this source, or a null string if None.
  */
  def versionString : String = {
    blockVector("cexversion").size match {
      case 0 => ""
      case 1 => blockVector("cexversion")(0)
      case n: Int =>  throw new Exception("Only one cexversion block allowed: found " + n)
    }
  }


  /** Vector of labelled blocks of CEX strings. */
  def rawBlocks = rawCex.split("#!").filter(_.nonEmpty).toVector

  /** Each block of data as a vector of non-empty, non-comment lines. */
  def blocksContentLines: Vector[Vector[String]] = {
    val content = for (b <- rawBlocks if b.split("\n").size > 1) yield {
      val lns = b.split("\n").toVector
      lns.filterNot(_.startsWith("#")).filter(_.nonEmpty)
    }
    content.filter(_.nonEmpty)
  }



  /** Map of block labels to one or more data sets. */
  def blockMap : scala.collection.immutable.Map[String,Vector[String]] = {
    val blocksToContent = scala.collection.mutable.Map[String, Vector[String]]()

    for (lns <- blocksContentLines) {
      if (blocksToContent.keySet.contains(lns(0))) {
        val v = blocksToContent(lns(0)) :+ lns.drop(1).mkString("\n")
        blocksToContent(lns(0)) = v

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
  val blockLabels: Set[String] = {
    blockMap.keySet
  }


  /** Find content for block label.
  */
  def blockVector(blockLabel: String) : Vector[String] = {
    if (blockLabels.contains(blockLabel)) {
      blockMap(blockLabel)
    } else {
      Vector[String]()
    }
  }


  require (labels.union(blockLabels) == labels, "Invalid block label in " + blockLabels)

  if (blockLabels.contains("citedata")) {
    require(blockLabels.contains("citecatalog"), "CITE Collection data must be documented in a citectalog block")
  } else {}



  def dimensions = {
    println(s"Library has ${blockMap.keySet.size} block types, with the following dimensions:")
    for (b <- blockMap.keySet) {
      val blockStrings = blockMap(b)
      val blockContent =
      println(s"  ${b}: ${blockStrings.size} blocks with " + blockStrings.size + " content lines")
    }
  }

}
