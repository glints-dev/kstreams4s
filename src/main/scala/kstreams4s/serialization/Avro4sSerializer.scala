package kstreams4s.serialization

import com.sksamuel.avro4s.RecordFormat
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.kafka.common.serialization.Serializer

final case class Avro4sSerializer[T](format: RecordFormat[T])
    extends Serializer[T]() {
  private val inner = new KafkaAvroSerializer()

  override def configure(
      configs: java.util.Map[String, _],
      isKey: Boolean
  ): Unit =
    inner.configure(configs, isKey)

  override def serialize(topic: String, maybeData: T): Array[Byte] =
    Option(maybeData)
      .map(data => inner.serialize(topic, format.to(data)))
      .getOrElse(null)

  override def close(): Unit = inner.close()
}
