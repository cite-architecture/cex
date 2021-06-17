# `cex`

## What it is

`cex` is a cross-platform library for working with data in CITE Exchange format (CEX).

(See the CEX specification at <https://cite-architecture.github.io/citedx/>.)

## Current version: 6.5.1


Status:  **early development**. [Release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)


## Documentation

<https://cite-architecture.github.io/cex/>


## Using, building, testing

`cex` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12. Binaries for all three versions are available from the Nexus repository on <terracotta.hpcc.uh.edu/nexus>.

    resolvers += "Nexus" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/"

and  add this to your library dependencies:

    "edu.holycross.shot" %% "cex" % VERSION

For maven, ivy or gradle equivalents, refer to <https://bintray.com/neelsmith/maven/cex>.



To build from source and test for a given version, use normal sbt commands (`compile`, `test` ...).

You can also test or run tasks against all versions, using `+` before the task name.  E.g.,  `sbt "+ test"` runs the `test` task against all versions.
