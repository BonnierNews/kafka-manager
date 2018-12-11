/**
 * Copyright 2017 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */
package kafka.manager.model

import org.scalatest.FunSuite

/**
  * @author fuji-151a
  */
class KafkaVersionTest extends FunSuite {

  private val kafkaVersionMap: Map[String, KafkaVersion] = Map(
    "1.1.0" -> Kafka_1_1_0,
    "2.0.0" -> Kafka_2_0_0,
    "2.1.0" -> Kafka_2_1_0
  )

  test("apply method: supported version.") {
    kafkaVersionMap.foreach(v => assertResult(v._2)(KafkaVersion(v._1)))
  }

  test("apply method: Not supported version.") {
    val expected: String = "0.7.0.0"
    intercept[IllegalArgumentException] {
      KafkaVersion(expected)
    }
  }

  test("check supportedVersions") {
    assertResult(kafkaVersionMap)(KafkaVersion.supportedVersions)
  }

  test("Sort formSelectList") {
    val expected: IndexedSeq[(String,String)] = Vector(
      ("1.1.0","1.1.0"),
      ("2.0.0","2.0.0"),
      ("2.1.0","2.1.0")
    )
    assertResult(expected)(KafkaVersion.formSelectList)
  }

  test("unapply") {
    kafkaVersionMap.filterNot(_._1.contains("beta")).foreach(v => assertResult(Some(v._1))(KafkaVersion.unapply(v._2)))
  }
}
