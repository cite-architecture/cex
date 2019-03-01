package edu.holycross.shot.cex

import java.io.File
import scala.io.Source


/** Utility objectn
*/
object DataCollector {





  def filesInDir(dir: File, extension: String): Vector[File] = {
    if (! dir.exists) {
      throw new Exception("DataCollector: no directory " + dir + " found.")
    } else {
      val fileVector = dir.listFiles.filter(_.isFile).toVector
      fileVector.filter(_.getName.endsWith(extension)).sorted
    }
  }

  /** Find set of files in a given directory with name
  * matching a given extension.
  *
  * @param dir Directory to look in.
  * @param extension File extension to match.
  */
  def filesInDir(dir: String, extension: String): Vector[File] = {
    val libraryDir = new File(dir)
    filesInDir(libraryDir, extension)
  }


  /** Create a single String concatenating content
  * of all CEX files in a given directory having a given file extension.
  *
  * @param dir Directory to collect content from.
  * @param extension File name extension of files to collect.
  * @param dropLines Number of lines to drop from beginning of file,
  * 0 if none should be dropped.
  */
  def compositeFiles(dir: String, extension: String = "cex", dropLines: Int = 0): String = {
    def fileSet = filesInDir(dir, extension)
    def txts = for (f <- fileSet) yield {
      val lines = scala.io.Source.fromFile(f).getLines.toVector.drop(dropLines)
      lines.mkString("\n")
    }
    txts.toSeq.mkString("\n") + "\n"
  }

}
