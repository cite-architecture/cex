package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class JsAccessSpec extends FlatSpec {

  "A CexParser" should "be exposed to Javascript" in {

    val multiCatalog = """
#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.va_xml:#book,line#Homeric epic#Iliad#HMT project archival XML edition##true#grc

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.msA:#book,line#Homeric epic#Iliad#HMT project diplomatic edition##true#grc
"""
    val cex = CexParser(multiCatalog)
    val textCatalog = cex.blockString("ctscatalog")
    val lines = textCatalog.split("\n")
    val expectedLines = 3
    assert(lines.size == expectedLines)
  }
}
