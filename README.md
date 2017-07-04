# `cex`

## What it is

`cex` is a cross-platform library for working with data in CITE Exchange format.

## Current version: 6.0.0


Status:  **early development**. [Release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)

## Using, building, testing

`cex` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12.  Binaries for all platforms are available from jcenter.  If you are using sbt, include `Resolver.jcenterRepo`in your list of resolvers

    resolvers += Resolver.jcenterRepo

and  add this to your library dependencies:

    "edu.holycross.shot" %% "cex" % VERSION

For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/cex>.



To build from source and test for a given version, use normal sbt commands (`compile`, `test` ...).

You can also test or run tasks against all versions, using `+` before the task name.  E.g.,  `sbt "+ test"` runs the `test` task against all versions.
