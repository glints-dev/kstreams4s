import sbt._

object Dependencies {
  lazy val logback = "1.2.10"
  lazy val confluent = "6.2.1"
  lazy val kafka = "2.8.1"

  lazy val kafkaStreamsScala =
    "org.apache.kafka" %% "kafka-streams-scala" % kafka
  lazy val kafkaAvroSerializer =
    "io.confluent" % "kafka-avro-serializer" % confluent
  lazy val kafkaStreamsAvroSerde =
    "org.apache.kafka" %% "kafka-streams-scala" % kafka
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % "3.3.4"
  lazy val avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "4.0.12"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.10" % Test
  lazy val kafkaStreamsTestUtils =
    "org.apache.kafka" % "kafka-streams-test-utils" % kafka % Test
}
