package edu.holycross.shot.cex

import scala.scalajs.js
import js.annotation.JSExport


@JSExport case class CexParser (rawCex: String) {

  val sections = rawCex.split("#!").filter(_.nonEmpty).filter(_.startsWith("#"))

  val blockLabels = sections.map(_.split("\n")(0))

  def hasBlock(label: String): Boolean = {
    blockLabels.indexOf(label) match {
      case -1 => false
      case _ => true
    }
  }

  def block(label: String) : Option[String] = {
    if (hasBlock(label)) {
      val labelledBlock = sections(blockLabels.indexOf(label))
      // drop label
      Some(labelledBlock.split("\n").drop(1).mkString("\n"))
    } else {
      None
    }
  }




}
