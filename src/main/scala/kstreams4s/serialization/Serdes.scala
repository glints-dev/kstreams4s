package kstreams4s.serialization

import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.common.serialization.{Serdes => SerdesS}

object Serdes {
  def avro4sSerde[T](format: RecordFormat[T]) =
    SerdesS.serdeFrom(Avro4sSerializer(format), Avro4sDeserializer(format))
}
