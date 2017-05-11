package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class Cex_1_0_ParserSpec extends FlatSpec {

val tinyCex = """# This is a small CEX string.
# It uses the pound sign for its delimiter.

#!cexversion
1.0.0
#!citelibrary
name#demo
urn#urn:cite2:cex:testdata.2017_1:parsertest
license#CC Share Alike.  For details, see more info.
#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:citedemo:arabic.quran.v1:#surah/ayah#Classical Arabic examples#The Quran#Arabic. Text from http://tanzil.net. Creative Commons Attribution 3.0 License##true
#!ctsdata
urn:cts:citedemo:arabic.quran.v1:1.1#بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ
urn:cts:citedemo:arabic.quran.v1:1.2#الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ
urn:cts:citedemo:arabic.quran.v1:1.3#الرَّحْمَنِ الرَّحِيمِ
urn:cts:citedemo:arabic.quran.v1:1.4#مَالِكِ يَوْمِ الدِّينِ
urn:cts:citedemo:arabic.quran.v1:1.5#إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ
urn:cts:citedemo:arabic.quran.v1:1.6#اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ
urn:cts:citedemo:arabic.quran.v1:1.7#صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ
urn:cts:citedemo:arabic.quran.v1:2.1#بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ الم
urn:cts:citedemo:arabic.quran.v1:2.2#ذَلِكَ الْكِتَابُ لَا رَيْبَ فِيهِ هُدًى لِلْمُتَّقِينَ
"""

  "A CexParser" should "parse its input into a map of blocks" in {
    val cex = CexParser(tinyCex)
    val expectedBlocks = 4
    assert(cex.blocks.size == expectedBlocks)
  }

  it should "identify a version if provided" in {
    val cex = CexParser(tinyCex)
    assert(cex.versionString == "1.0.0")
  }

  it should "report an empty string if no version is defined" in {
    val noVersion = """#!ctsdata
urn:cts:citedemo:arabic.quran.v1:1.1#بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ
urn:cts:citedemo:arabic.quran.v1:1.2#الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ
"""
    val cex = CexParser(noVersion)
    assert (cex.versionString == "")
  }

  it should "supply an Option[String] for version" in {
    val noVersion = """#!ctsdata
urn:cts:citedemo:arabic.quran.v1:1.1#بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ
urn:cts:citedemo:arabic.quran.v1:1.2#الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ
"""
    val versionless = CexParser(noVersion)
    assert(versionless.version == None)

    val withversion = CexParser(tinyCex)
    assert(withversion.version == Some("1.0.0"))
  }

  it should "recognize a ctscatalog block" in {
    val expectedCatalog = """urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:citedemo:arabic.quran.v1:#surah/ayah#Classical Arabic examples#The Quran#Arabic. Text from http://tanzil.net. Creative Commons Attribution 3.0 License##true"""

    val cex = CexParser(tinyCex)
    val catalogV =  cex.block("ctscatalog")
    assert (catalogV.size == 1)
    assert (catalogV(0) == expectedCatalog)
  }

  it should "verify that all block labels are valid" in {
    val badLabel = """# This is a small CEX string.
# It uses an invalid label.
#!bogus
1.0.0
"""


    try {
      val cex = CexParser(badLabel)
      fail("Should have generated assertion error.")
    } catch {
      case err: IllegalArgumentException =>  {
        assert(err.getMessage() == "requirement failed: Invalid block label in Set(bogus)")
      }

      case thr : Throwable => fail("Should have generated illegal argument error: " + thr)
    }
  }



  it should "return an empty Vector when asked for a non-existent block" in {
    val cex = CexParser(tinyCex)
    val noBlock = cex.block("boguslabel")
    val v =   Vector[String]()
    assert (noBlock == v)
  }

  it should "require that citedata blocks be accompanied by a citecatalog block" in {
    val citedata = """#!citedata
#Venetus A images
URN#Caption#Rights
urn:cite2:hmt:vaimg:img1#Folio 1 recto, natural light#public domain
urn:cite2:hmt:vaimg:img1a#Folio 1 recto, detail in UV light#public domain

#!citedata
#Upsilon 1.1 images
URN#Caption#Rights
urn:cite2:hmt:e3eimg:img1#Folio 1 recto, natural light#public domain
urn:cite2:hmt:e3eimg:img2#Folio 1 verso, natural light#public domain


#!citecatalog
# CEX Parser does not validate *contents* of blocks, but *does* ensure
# that there must be a catalog block if you have a citedata block ....


collection#urn:cite2:hmt:vaimg.v1:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.v1.caption:##CC-attribution-share-alike

property#urn:cite2:hmt:msA.v1.urn:#Image URN#Cite2Urn#
property#urn:cite2:hmt:msA.v1.caption:#Caption#String#
property#urn:cite2:hmt:msA.v1.rights:#License for binary image data#String#CC-attribution-share-alike,public domain
"""
    val cex = CexParser(citedata)
    val citedatablocks = cex.block("citedata")

    assert(citedatablocks.size == 2)


  }

  it should "accept multiple citedata blocks in a single CEX source" in  {
    val src = """#!citecatalog
collection#urn:cite2:hmt:vaimg.v1:#Images of the Venetus A manuscriptscript#urn:cite2:hmt:vaimg.v1.caption:##CC-attribution-share-alike

property#urn:cite2:hmt:msA.v1.urn:#Image URN#Cite2Urn#
property#urn:cite2:hmt:msA.v1.caption:#Caption#String#
property#urn:cite2:hmt:msA.v1.rights:#License for binary image data#String#CC-attribution-share-alike,public domain
"""
    val cex = CexParser(src)
    assert(cex.block("citecatalog").size == 1)

  }

  it should "throw an exception if it sees a citedata block but no citecatalog block" in {
    val citedata = """#!citedata
#Venetus A images
URN#Caption#Rights
urn:cite2:hmt:vaimg:img1#Folio 1 recto, natural light#public domain
urn:cite2:hmt:vaimg:img1a#Folio 1 recto, detail in UV light#public domain
"""
    try {
      val cex = CexParser(citedata)
      fail("Should not have parsed citedata without catalog")
    } catch {
      case iae: IllegalArgumentException => assert (iae.getMessage() == "requirement failed: CITE Collection data must be documented in a citectalog block")
      case thr: Throwable => fail("Should have thrown IllegalArgumentException: " + thr)

    }
  }




}
