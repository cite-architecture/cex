package edu.holycross.shot


/** Provides an object for parsing citable resources in CEX serialization.
* See https://github.com/cite-architecture/citedx for documentation
* of the CEX format.
*
* ==Overview==
* The main class is the [[CexParser]].  It is constructucted from
* a string in CEX format, and provides functions for working with
* distinct blocks of the data source.  For example:
*
* {{{
* val cex = CexParser(cexSourceString)
* // get a map of all blocks labels to a Vector of one or
* // more string data sets:
* val blockMap = cex.blockMap
* // get a single string concatenating all data for a single
* // block type:
* val ctsCatalog = cex.blockString("ctscatalog")
* }}}
*
* In the JVM environment, the [[CexParserSource]] object provides
* a single-step factory function for creating parsers from files:
*
* {{{
* val cex = CexParserSource.fromFile("filename.cex")
* }}}
*/
package object cex {

  /** Exhaustive set of valid labels for CEX blocks.
  */
  val labels = Set(
    "cexversion",
    "citelibrary",
    "ctsdata",
    "ctscatalog",
    "citedata",
    "citecollections",
    "citeproperties",
    "imagedata",
    "relations",
    "datamodels"

  )

}
