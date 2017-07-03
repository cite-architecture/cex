package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class Cex_1_2_ParserSpec extends FlatSpec {


  val tinyCex = """#!citelibrary
name#demo
urn#urn:cite2:cex:testcoll:hdt1node
license#public domain

#!ctscatalog

urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:greekLit:tlg0016.tlg001.loebeng:#book/section#Herodotus#Histories#English. trans. Godley [1921]##true

#!ctsdata

urn:cts:greekLit:tlg0016.tlg001.loebeng:1.0#This is the Showing forth of the Inquiry of Herodotus of Halicarnassos, to the end that neither the deeds of men may be forgotten by lapse of time, nor the works great and marvellous, which have been produced some by Hellenes and some by Barbarians, may lose their renown; and especially that the causes may be remembered for which these waged war with one another.

#!citecatalog
collection#urn:cite2:hmt:msA.v1:#Pages of the Venetus A manuscriptscript#urn:cite2:hmt:msA.v1.label:#urn:cite2:hmt:msA.v1.sequence:#CC-attribution-share-alike

property#urn:cite2:hmt:msA.v1.urn:#URN#Cite2Urn#
property#urn:cite2:hmt:msA.v1.label:#Label#String#
property#urn:cite2:hmt:msA.v1.siglum:#Manuscript siglum#String#
property#urn:cite2:hmt:msA.v1.sequence:#Page sequence#Number#
property#urn:cite2:hmt:msA.v1.rv:#Recto or Verso#String#recto,verso
property#urn:cite2:hmt:msA.v1.codex:#Codex URN#Cite2Urn#

#!citedata
siglum#sequence#urn#rv#label#codex
msA#1#urn:cite2:hmt:msA.v1:1r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 1r#urn:cite2:hmt:codex:msA
msA#2#urn:cite2:hmt:msA.v1:1v#verso#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 1v#urn:cite2:hmt:codex:msA
msA#3#urn:cite2:hmt:msA.v1:2r#recto#Marcianus Graecus Z. 454 (= 822) (Venetus A) folio 2r#urn:cite2:hmt:codex:msA


#!relations
//  SVO triplets :

urn:cts:greekLit:tlg0012.tlg001.msA:1.title#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.2022,0.211,0.1732,0.0203
urn:cts:greekLit:tlg0012.tlg001.msA:1.1#urn:cite2:cite:dseverbs.r1:illustratedBy#urn:cite2:hmt:vaimg.r1:VA012RN_0013@0.0611,0.2252,0.4675,0.0901

"""


  "A 1.2 CexParser" should "recognize cite collections" in {
    val cex = CexParser(tinyCex)
    cex match {
      case cparser : CexParser => assert(true)
      case _ => fail("should have created a CexParser")
    }
  }

  it should "provide basic dimensions of the library" in pending /*{
    val cex = CexParser(tinyCex)
    val dimm = cex.dimensions
  }*/

  it should "split raw CEX into a vector of labelled blocks" in pending
  // rawBlocks function


  it should "split reduce labelled blocks to labelled content lines" in pending
  // blocksContent function, a Vector[Vector[String]]

  it should "convert labelled content lines to a map" in pending
  //blockMap function

  it should "repot blocks present in this library" in pending
  // blocks function


  it should "find content lines for a block label" in pending
  // block function




}
