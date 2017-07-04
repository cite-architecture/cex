package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class DseParsingSpec extends FlatSpec {


    val dseSrc = """
//ABOUT THIS FILE////////////////////////////////////////////////
//
// This file is in CEX format (https://github.com/cite-architecture/citedx).
// It contains a complete DSE model of one line of the
// *Iliad* in the Venetus A manuscript.
//
/////////////////////////////////////////////////////////////////
#!cexversion
3.0.0

#!citelibrary
name#Toy data  set to test DSE data model
urn#urn:cite2:hmt:toys.2017a:toydse
license#Public domain

namespace#hmt#http://www.homermultitext.org/citens/hmt
namespace#greekLit#http://chs.harvard.edu/ctsns/greekLit

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.msA2017a:#book,line#Homeric Poetry#Iliad#Venetus A manuscript##true#grc

#!ctsdata
urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.1#Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος
urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.2#οὐλομένην· ἡ μυρί' Ἀχαιοῖς ἄλγε' ἔθηκεν·
urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.3#πολλὰς δ' ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν
urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.4#ἡρώων· αὐτοὺς δὲ ἑλώρια τεῦχε κύνεσσιν
urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.5#οἰωνοῖσί τε πᾶσι· Διὸς δ' ἐτελείετο βουλή·


#!citecollections
Collection URN#Desription#Labelling Ordering License
urn:cite2:hmt:msA.2017a:#Pages of the Venetus A manuscriptscript#urn:cite2:hmt:msA.2017a.label:#urn:cite2:hmt:msA.2017a.sequence:#CC-attribution-share-alike
urn:cite2:hmt:vaimg.2017a:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.2017a.caption:##CC-attribution-share-alike
urn:cite2:hmt:dseiliad.2017a:#Diplomatic scholarly edition of Venetus A Iliad#urn:cite2:hmt:dseiliad.2017a.label:##CC-attribution-share-alike


#!citeproperties

// Pages
urn:cite2:hmt:msA.2017a.urn:#URN#Cite2Urn#
urn:cite2:hmt:msA.2017a.label:#Label#String#
urn:cite2:hmt:msA.2017a.siglum:#Manuscript siglum#String#
urn:cite2:hmt:msA.2017a.sequence:#Page sequence#Number#
urn:cite2:hmt:msA.2017a.rv:#Recto or Verso#String#recto,verso
urn:cite2:hmt:msA.2017a.codex:#Codex URN#Cite2Urn#

// Images
urn:cite2:hmt:vaimg.2017a.urn:#URN#Cite2Urn#
urn:cite2:hmt:vaimg.2017a.caption:#Caption#String#
urn:cite2:hmt:vaimg.2017a.rights:#Rights#String#

// DSE relations
urn:cite2:hmt:dseiliad.2017a.urn:#URN#Cite2Urn#
urn:cite2:hmt:dseiliad.2017a.label:#Label#String#
urn:cite2:hmt:dseiliad.2017a.text:#Text passage#CtsUrn#
urn:cite2:hmt:dseiliad.2017a.image:#Documentary image#Cite2Urn#
urn:cite2:hmt:dseiliad.2017a.surface:#Physical surface#Cite2Urn#

#!citedata
siglum#sequence#urn#rv#label#codex
msA#1#urn:cite2:hmt:msA.v1:12r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 12 recto#urn:cite2:hmt:codex:msA

#!citedata
urn#label#text#image#surface
urn:cite2:hmt:dseiliad.2017a:1_1#Context for Iliad 1.1#urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.1#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.0611,0.2252,0.4675,0.0901#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:dseiliad.2017a:1_2#Context for Iliad 1.2#urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.2#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1632,0.2523,0.3323,0.0248#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:dseiliad.2017a:1_3#Context for Iliad 1.3#urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.3#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1642,0.2725,0.3323,0.0248#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:dseiliad.2017a:1_4#Context for Iliad 1.4#urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.4#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1652,0.2905,0.3463,0.0255#urn:cite2:hmt:msA.2017a:12r
urn:cite2:hmt:dseiliad.2017a:1_5#Context for Iliad 1.5#urn:cts:greekLit:tlg0012.tlg001.msA2017a:1.5#urn:cite2:hmt:vaimg.2017a:VA012RN_0013@0.1632,0.3116,0.3273,0.0255#urn:cite2:hmt:msA.2017a:12r

#!citedata
urn#caption#rights
urn:cite2:hmt:vaimg.2017a:VA012RN_0013.2017#Natural light photograph of Venetus A: Marcianus Graecus Z. 454 (= 822), folio 12, recto#This image was derived from an original ©2007, Biblioteca Nazionale Marciana, Venezie, Italia. The derivative image is ©2010, Center for Hellenic Studies. Original and derivative are licensed under the Creative Commons Attribution-Noncommercial-Share Alike 3.0 License. The CHS/Marciana Imaging Project was directed by David Jacobs of the British Library.

#!datamodels
urn:cite2:hmt:vaimg.2017a:#urn:cite2:cite2:datamodels2017a:image#CITE Image data model.
urn:cite2:cite2:datamodels.2017a:dse#DSE model#Diplomatic Scholarly Edition (DSE) model.  See documentation at <https://github.com/cite-architecture/dse>.
"""

  val cex = CexParser(dseSrc)
  "A 3.0 CexParser" should "create a parser for DSE model data" in {
    cex match {
      case cparser : CexParser => assert(true)
      case _ => fail("should have created a CexParser")
    }
  }

  it should "report version number if included" in {
    assert (cex.versionString == "3.0.0")
    assert(cex.version == Some("3.0.0"))
  }

  it should "split raw CEX content into a vector of labelled blocks" in {
    assert(cex.rawBlocks.size == 11)
  }



  it should "split reduce labelled blocks to labelled content lines" in {
    val labelled = cex.blocksContentLines
    assert(labelled.size == 10)

    val expectedLabels = Vector(
      "cexversion", "citelibrary", "ctscatalog", "ctsdata", "citecollections", "citeproperties", "citedata", "citedata", "citedata", "datamodels")
    val labels = labelled.map(_(0))
    assert(labels == expectedLabels)

  }


  it should "convert labelled content lines to a map" in {
    val mapped = cex.blockMap
    assert(mapped.size == 8)
    val expectedKeys = Set("citeproperties", "citelibrary", "citedata", "ctsdata", "cexversion", "datamodels", "citecollections", "ctscatalog")
    assert(mapped.keySet == expectedKeys)

  }


  it should "report the set of blocks present in this library" in {
    val blockSet = cex.blockLabels
    assert(blockSet.size == 8)
    val expectedBlocks =  Set("citeproperties", "citelibrary", "citedata", "ctsdata", "cexversion", "datamodels", "citecollections", "ctscatalog")
    assert(blockSet == expectedBlocks)

  }

  it should "find a vector of content blocks for a label" in {
    val citeDataBlocks = cex.blockVector("citedata")
    assert(citeDataBlocks.size == 3)
  }

  it should "create a single string of content lines for a label" in {
    val dataString = cex.blockString("citedata")
    assert(dataString.split("\n").size == 10)
  }

  it should "report none if version is requested but none present" in {
    val veryTiny = """
// Empty CITE library: CEX source with only a single
// citelibrary block, and no version indicated.

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
