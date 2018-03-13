---
layout: page
title: Quick start
---


## Purpose

The `cex` library is primarily intended to simplify access to CEX data for other code libraries, rather than end users.  For most purposes, you can use CEX-formatted data directly from higher-level libraries, like the [`ohco2` library for working with citable texts](https://github.com/cite-architecture/ohco2).

If you need to parse CEX data directly, however, you can use the `cex` library to get access to specific blocks of a CEX source.

## Parse CEX data

Import the package:

```tut:silent
import edu.holycross.shot.cex._
```

Parse a `String`of CEX data:

```tut:silent
val cexStr = """
#!cexversion
3.0

"""

val cex = CexParser(cexStr)
```


In the JVM environment, you can parse a file:

```tut:silent
val cex = CexParserSource.fromFile("jvm/src/test/resources/sample.cex")
```

## Working with parsed data

Map CEX block labels to `Vector`s of one or
more string data sets, and get the string content of a specified block type:

```tut:silent
val blockMap = cex.blockMap
val ctsCatalog = cex.blockString("ctscatalog")
val ctsData = cex.blockString("ctsdata")
```




At this point, you could work with individual blocks of the CEX source using appropriate libraries for particular kinds of citable data.  For example, you can create a repository of canonically citable texts directly from CEX content containing a `ctscatalog` and `ctsdata` block.



    import edu.holycross.shot.ohco2._
    val concatenated  = "#!ctscatalog\n" + ctsCatalog + "\n#!ctsdata\n"  + ctsData
    val textRepository = TextRepository(concatenated)


See the documentation for the [`ohco2` library](https://cite-architecture.github.io/ohco2/).
