package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class DseDataModelSpec extends FlatSpec {

  val dseSrc = """
//ABOUT THIS FILE////////////////////////////////////////////////
//
// This file is in CEX format (https://github.com/cite-architecture/citedx).
// It contains a complete DSE model of one line of the
// *Iliad* in the Venetus A manuscript.
//
/////////////////////////////////////////////////////////////////

#!cexversion
///
// Version 3.0 supports all content blocks needed to implement the DSE model.
3.0

#!citelibrary
name#Demo of DSE structure: Venetus A manuscript, folio 12 recto
urn#urn:cite2:dse:demo.2017a:va12r
license#public domain

#!ctscatalog
//
// This demo catalogs one text: a specific version of the *Iliad*.
//

urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:greekLit:tlg0012.tlg001.msA:#book/line#Homeric poetry#Iliad#HMT project edition of the Venetus A##true

#!ctsdata
//
// This demo contains text for a single manuscript line of the *Iliad*.
//

urn:cts:greekLit:tlg0012.tlg001.msA:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος


#!citecatalog
//
// There are three required collections in the DSE model:
// 1. a collection of text-bearing surfaces
// 2. a collection of documentary images
// 3. the catalog of DSE relations
//

// Text-bearing surfaces:
collection#urn:cite2:hmt:msA.v1:#Pages of the Venetus A manuscriptscript#urn:cite2:hmt:msA.v1.label:#urn:cite2:hmt:msA.v1.sequence:#CC-attribution-share-alike

property#urn:cite2:hmt:msA.v1.urn:#URN#Cite2Urn#
property#urn:cite2:hmt:msA.v1.label:#Label#String#
property#urn:cite2:hmt:msA.v1.siglum:#Manuscript siglum#String#
property#urn:cite2:hmt:msA.v1.sequence:#Page sequence#Number#
property#urn:cite2:hmt:msA.v1.rv:#Recto or Verso#String#recto,verso
property#urn:cite2:hmt:msA.v1.codex:#Codex URN#Cite2Urn#

// Documentary images:
collection#urn:cite2:hmt:vaimg.2017a:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.2017a.caption:##CC-attribution-share-alike

property#urn:cite2:hmt:vaimg.2017a.urn:#URN#Cite2Urn#
property#urn:cite2:hmt:vaimg.2017a.caption:#Caption#String#
property#urn:cite2:hmt:vaimg.2017a.rights:#Rights#String#



#!citedata
//
// Data block for the collection of text-bearing surfaces.
// This demo includes a single manuscript page.
//
siglum#sequence#urn#rv#label#codex
msA#1#urn:cite2:hmt:msA.v1:12r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 12 recto#urn:cite2:hmt:codex:msA

#!citedata
//
// Data block for the collection of documentary images.
// This demo includes a single image documenting a single manuscript page.
//

urn#caption#rights
urn:cite2:hmt:vaimg.2017a:VA012RN_0013.2017#Natural light photograph of Venetus A: Marcianus Graecus Z. 454 (= 822), folio 12, recto#This image was derived from an original ©2007, Biblioteca Nazionale Marciana, Venezie, Italia. The derivative image is ©2010, Center for Hellenic Studies. Original and derivative are licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. The CHS/Marciana Imaging Project was directed by David Jacobs of the British Library.

#!imagedata
//
// This demo extends a single collection of images.
//

urn:cite2:hmt:vaimg.2017a:#local string string#./#urn:cite2:hmt:vaimg.2017a.rights:


#!relations
//
// The DSE model requires statements relating:
// 1. text passage to text-bearing surface
// 2. text passage to documentary image
// 3. text-bearing surface to documentary image
//
// In this CEX block, each of those relations is expressed with
// a pair of SVO statements, using either the inverse pair of verbs
// "urn:cite2:cite:dseverbs.2017a:appearsOnhasOnIt"/urn:cite2:cite:dseverbs.2017a:appearsOn"
# or
// "urn:cite2:cite:dseverbs.2017a:illustrates/urn:cite2:cite:dseverbs.2017a:isIllustratedBy".
//

// 1. Relation of text passages to text-bearing surface:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:appearsOn#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:hasOnIt#urn:cts:greekLit:tlg0012.tlg001.msA:1.1

// 2. Relation of text passages to documentary image:
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.VA012RN_0013.v1@0.0611,0.2252,0.4675,0.0901
urn:cite2:hmt:vaimg.VA012RN_0013.v1@0.0611,0.2252,0.4675,0.0901#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cts:greekLit:tlg0012.tlg001.msA:1.1

// 3. Relation of text-bearing surface to documentary image:
urn:cite2:hmt:msA.2017a:12r#urn:cite2:cite:dseverbs.2017a:illustratedBy#urn:cite2:hmt:vaimg.2017a:VA012RN_0013
urn:cite2:hmt:vaimg.2017a:VA012RN_0013#urn:cite2:cite:dseverbs.2017a:illustrates#urn:cite2:hmt:msA.2017a:12r

"""

  val cex = CexParser(dseSrc)
  "A 3.0 CexParser" should "create a parser for DSE model data" in {
    cex match {
      case cparser : CexParser => assert(true)
      case _ => fail("should have created a CexParser")
    }
  }



  it should "split raw CEX content into a vector of labelled blocks" in {
    assert(cex.rawBlocks.size == 10)
  }



  it should "split reduce labelled blocks to labelled content lines" in {
    val labelled = cex.blocksContentLines
    assert(labelled.size == 9)

    val expectedLabels = Vector( "cexversion",
      "citelibrary", "ctscatalog", "ctsdata",
      "citecatalog", "citedata", "citedata",
      "imagedata", "relations")
    val labels = labelled.map(_(0))
    assert(labels == expectedLabels)

  }


  it should "convert labelled content lines to a map" in {
    val mapped = cex.blockMap
    assert(mapped.size == 8)
    val expectedKeys = Set( "cexversion",
      "citelibrary", "ctscatalog", "ctsdata", "citecatalog",
       "citedata", "imagedata", "relations")
    assert(mapped.keySet == expectedKeys)

  }


  it should "report the set of blocks present in this library" in {
    val blockSet = cex.blockLabels
    assert(blockSet.size == 8)
    val expectedBlocks = Set("cexversion",
      "citelibrary", "ctscatalog", "ctsdata", "citecatalog",
       "citedata", "imagedata", "relations")
    assert(blockSet == expectedBlocks)

  }

  it should "find a vector of content blocks for a label" in {
    val citeDataBlocks = cex.blockVector("citedata")
    assert(citeDataBlocks.size == 2)
  }

  it should "create a single string of content lines for a label" in {
    val dataString = cex.blockString("citedata")
    assert(dataString.split("\n").size == 4)
  }




  it should "report version number if included" in {
    println("CEX VERSIOn STRING " + cex.versionString)

    assert (cex.versionString == "3.0")
    assert(cex.version == Some("3.0"))
  }




  it should "report none if version is requested but none present" in {
    val veryTiny = """
// Empty CITE library: CEX source with only a single
// citelibrary block, and no version indicated.
//
#!citelibrary
name#Demo of tiny CEX file with no version indicated
urn#urn:cite2:dse:demo.2017a:noversion
license#public domain
"""
    val tiny = CexParser(veryTiny)
    assert(tiny.version == None)
  }

  it should "provide basic dimensions of the library" in  {
    val dimm = cex.dimensions
    assert(dimm.size == 8)
  }

  it should "have a function for pretty-printing dimensions" in {
    cex.printDimensions
  }
}