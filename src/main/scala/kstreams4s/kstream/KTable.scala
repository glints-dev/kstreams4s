package kstreams4s.kstream

import org.apache.kafka.streams.kstream.{KTable => KTableJ, Named}
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream.{KTable => KTableS, Materialized}

final case class KTable[K, V](override val inner: KTableJ[K, V])
    extends KTableS[K, V](inner) {
  def from[K, V](table: KTableS[K, V]) = KTable(table.inner)

  def leftJoinOption[VO, VR](other: KTable[K, VO])(
      joiner: V => Option[VO] => VR
  ) =
    this.from(
      this.leftJoin(other)((left: V, right: VO) => joiner(left)(Option(right)))
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(named: Named)(
      joiner: V => Option[VO] => VR
  ) =
    this.from(
      this.leftJoin(other, named)((left: V, right: VO) =>
        joiner(left)(Option(right))
      )
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  )(joiner: V => Option[VO] => VR) =
    this.from(
      this.leftJoin(other, materialized)((left: V, right: VO) =>
        joiner(left)(Option(right))
      )
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(named: Named)(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  )(joiner: V => Option[VO] => VR) =
    this.from(
      this.leftJoin(other, named, materialized)((left: V, right: VO) =>
        joiner(left)(Option(right))
      )
    )
}
