package kstreams4s.serialization

import com.sksamuel.avro4s.RecordFormat
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.IndexedRecord
import org.apache.kafka.common.serialization.Deserializer

final case class Avro4sDeserializer[T](format: RecordFormat[T])
    extends Deserializer[T]() {
  private val inner = new KafkaAvroDeserializer()

  override def configure(
      configs: java.util.Map[String, _],
      isKey: Boolean
  ): Unit =
    inner.configure(configs, isKey)

  override def deserialize(topic: String, maybeData: Array[Byte]): T =
    Option(maybeData)
      .filter(_.nonEmpty)
      .map { data =>
        format.from(
          inner.deserialize(topic, data).asInstanceOf[IndexedRecord]
        )
      }
      .getOrElse(null.asInstanceOf[T])

  override def close(): Unit = inner.close()
}
