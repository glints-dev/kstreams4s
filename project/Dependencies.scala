import sbt._

object Dependencies {
  val logback = "1.2.10"
  val confluent = "6.2.1"
  val kafka = "2.8.1"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.10" % Test
  lazy val kafkaStreamsScala =
    "org.apache.kafka" %% "kafka-streams-scala" % kafka
  lazy val kafkaStreamsTestUtils =
    "org.apache.kafka" % "kafka-streams-test-utils" % kafka % Test
  lazy val kafkaAvroSerializer =
    "io.confluent" % "kafka-avro-serializer" % confluent
  lazy val kafkaStreamsAvroSerde =
    "org.apache.kafka" %% "kafka-streams-scala" % kafka
  lazy val catsEffect = "org.typelevel" %% "cats-effect" % "3.3.4"
  lazy val avro4s = "com.sksamuel.avro4s" %% "avro4s-core" % "4.0.12"
}
