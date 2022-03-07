package kstreams4s

import org.apache.kafka.streams.kstream.{
  ValueJoiner => ValueJoinerJ
}

package object kstream {
  type LeftValueJoiner[V, VO, VR] = V => Option[VO] => VR
  type KeyExtractor[V, KO] = V => KO

  def leftValueJoiner[V, VO, VR](joiner: LeftValueJoiner[V, VO, VR]) = 
    (left: V, right: VO) => joiner(left)(Option(right))

  def leftValueJoinerAsJava[V, VO, VR](joiner: LeftValueJoiner[V, VO, VR]): ValueJoinerJ[V, VO, VR] = 
    leftValueJoinerAsJava[V, VO, VR](joiner)
}

