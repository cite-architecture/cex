package edu.holycross.shot.cex

import scala.scalajs.js
import scala.scalajs.js.annotation._

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map

/** A CEX parser making blocks of a CEX String available
* as a named map of label -> vectors of data.
*
* @param rawCex CEX data to parse.
*/
@JSExportAll case class CexParser (rawCex: String) {


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
      lns.filterNot(_.startsWith("//")).filter(_.nonEmpty)
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
  *
  * @param blockLabel Block to look for.
  */
  def blockVector(blockLabel: String) : Vector[String] = {
    if (blockLabels.contains(blockLabel)) {
      blockMap(blockLabel)
    } else {
      Vector[String]()
    }
  }


  /** Concatenate all content lines for a block type
  * into a single string.
  *
  * @param blockLabel Block to look for.
  */
  def blockString(blockLabel: String): String = {
    blockVector(blockLabel).mkString("\n")
  }


  /** Sum up counts of lines in Vector of integers.
  *
  * @param counts Vector of line counts.
  */
  def sum(counts: Vector[Int]): Int =
    if (counts.isEmpty) throw new IllegalArgumentException("sum of empty list")
    else if (counts.tail.isEmpty) counts.head
    else counts.head + sum(counts.tail)

  /** Report number of blocks and total number of lines for each block type.
  */
  def dimensions : scala.collection.immutable.Map[String,(Int, Int)] = {

    val tupleSet = for (b <- blockMap.keySet)  yield {
      val blockCount = blockMap(b).size
      val lineCounts =  for (s <- blockMap(b)) yield {
        s.split("\n").size
      }
      (b,blockCount, sum(lineCounts))
    }
    tupleSet.map{ case (s,i1,i2) => (s -> (i1,i2)) }.toMap
  }

  def printDimensions: Unit = {
    println(s"Library has ${blockMap.keySet.size} block types, with the following dimensions:")
    for ( (k,v) <- dimensions) {
      println(s"\t${k} has ${v._1} block(s) totalling ${v._2} content lines.")
    }
  }


  // Requirements for structure of CITE library:
  val invalid = blockLabels diff labels
  require (labels.union(blockLabels) == labels, "CEX parser: invalid block label(s) " + invalid)

  if (blockLabels.contains("citedata")) {
    require(blockLabels.contains("citecollections"), "CITE Collection data must be documented in a citecollections block")
    require(blockLabels.contains("citeproperties"), "CITE Collection data must be documented in a citeproperties block")

  } else {}

}
