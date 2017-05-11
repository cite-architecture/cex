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
    //assert(cex.block("cexversion") == Some()
/*
    val expectedCatalog = Some("""urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:citedemo:arabic.quran.v1:#surah/ayah#Classical Arabic examples#The Quran#Arabic. Text from http://tanzil.net. Creative Commons Attribution 3.0 License##true""")
    assert (cex.block("ctscatalog") == expectedCatalog)
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
      case err: java.lang.AssertionError => assert(true)
      case _ : Throwable => fail("Should have generated assertion error.")
    }
    */



  it should "report None when asked for a non-existent block" in {
    val cex = CexParser(tinyCex)
    val noBlock = cex.block("boguslabel")
    noBlock match {
      case None => assert(true)
      case _ => fail("Should have received a None option")
    }
  }

  it should "accept multiple citedata blocks in a single CEX source" in pending

  it should "require that citedata blocks be accompanied by a citecatalog block" in pending



}
