import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "2.8.1"
ThisBuild / organization := "com.glints"
ThisBuild / organizationName := "glints"

lazy val root = (project in file("."))
  .settings(
    name := "kstreams4s",
    libraryDependencies ++= Seq(
      kafkaStreamsScala,
      kafkaAvroSerializer,
      kafkaStreamsAvroSerde,
      avro4s,
      kafkaStreamsTestUtils,
      catsEffect,
      scalaTest
    ),
    resolvers += "Confluent Repo" at "https://packages.confluent.io/maven",

    // Scalafix config
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions += "-Xlint:unused",
    scalacOptions += "-deprecation",
    inThisBuild(
      scalafixDependencies ++= Seq(
        "org.scalalint" %% "rules" % "0.2.1",
        "com.github.liancheng" %% "organize-imports" % "0.6.0"
      )
    )
  )

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
