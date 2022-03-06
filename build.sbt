import Dependencies._

ThisBuild / scalaVersion := "2.13.7"
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
    resolvers += "Confluent Repo" at "https://packages.confluent.io/maven"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
