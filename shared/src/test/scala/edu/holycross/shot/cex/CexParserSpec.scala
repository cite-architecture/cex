package edu.holycross.shot.cex
import org.scalatest.FlatSpec




/**
*/
class CexParserSpec extends FlatSpec {

  val tinyCex = """# This is a small CEX string.
# It uses the pound sign for its delimiter.

#!cexversion
1.0.0
#!citerepo
name#demo
version#2017.1
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

  "A CexParser" should "parse its source into a map of labelled blocks" in {
    val cex = CexParser(tinyCex)
    val expectedBlocks = 4
    assert(cex.blocks.size == expectedBlocks)

    assert(cex.blocks("cexversion") == "1.0.0")

    val expectedCatalog = """urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online
urn:cts:citedemo:arabic.quran.v1:#surah/ayah#Classical Arabic examples#The Quran#Arabic. Text from http://tanzil.net. Creative Commons Attribution 3.0 License##true"""
    assert (cex.blocks("ctscatalog") == expectedCatalog)
  }

  it should "verify that all block labels are valid" in pending




}
