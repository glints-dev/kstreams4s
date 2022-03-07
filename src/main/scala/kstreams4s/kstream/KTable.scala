package kstreams4s.kstream

import org.apache.kafka.streams.kstream.{KTable => KTableJ, Named}
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream.{KTable => KTableS, Materialized}

final case class KTable[K, V](override val inner: KTableJ[K, V])
    extends KTableS[K, V](inner) {
  def from[K, V](table: KTableS[K, V]) = KTable(table.inner)

  def leftJoinOption[VO, VR](other: KTable[K, VO])(
      joiner: LeftValueJoiner[V, VO, VR]
  ) =
    this.from(
      this.leftJoin(other)(leftValueJoiner(joiner))
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(named: Named)(
      joiner: LeftValueJoiner[V, VO, VR]
  ) =
    this.from(
      this.leftJoin(other, named)(
        leftValueJoiner[V, VO, VR](joiner)
      )
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  )(joiner: V => Option[VO] => VR) =
    this.from(
      this.leftJoin(other, materialized)(
        leftValueJoiner[V, VO, VR](joiner)
      )
    )

  def leftJoinOption[VO, VR](other: KTable[K, VO])(named: Named)(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  )(joiner: V => Option[VO] => VR) =
    this.from(
      this.leftJoin(other, named, materialized)(
        leftValueJoiner[V, VO, VR](joiner)
      )
    )

  def leftJoinOption[VR, KO, VO](
      other: KTable[KO, VO]
  )(keyExtractor: KeyExtractor[V, KO])(joiner: LeftValueJoiner[V, VO, VR])(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  ) = this.from(
    this.leftJoin(
      other,
      keyExtractor,
      leftValueJoinerAsJava(joiner),
      materialized
    )
  )

  def leftJoinOption[VR, KO, VO](other: KTable[KO, VO])(
      keyExtractor: KeyExtractor[V, KO]
  )(joiner: LeftValueJoiner[V, VO, VR])(named: Named)(
      materialized: Materialized[K, VR, ByteArrayKeyValueStore]
  ): KTable[K, VR] =
    this.from(
      this.leftJoin(
        other,
        keyExtractor,
        leftValueJoinerAsJava(joiner),
        named,
        materialized
      )
    )
}
