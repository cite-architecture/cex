package edu.holycross.shot.cex



object JSMain {

  def main(args: Array[String]): Unit = {


    val miniCatalog = """
#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.va_xml:#book,line#Homeric epic#Iliad#HMT project archival XML edition##true#grc

#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:greekLit:tlg0012.tlg001.msA:#book,line#Homeric epic#Iliad#HMT project diplomatic edition##true#grc
"""
    val cex = CexParser(miniCatalog)
    val textCatalog = cex.blockString("ctscatalog")

    println("Parsed a CTS catalog.  Here it is.")
    println(textCatalog)
  }
}
