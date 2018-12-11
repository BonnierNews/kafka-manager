/**
 * Copyright 2015 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */

package kafka.manager

import kafka.manager.jmx.KafkaMetrics
import kafka.manager.model.Kafka_1_1_0
import kafka.manager.model.Kafka_2_1_0
import org.scalatest.FunSuite

/**
 * @author hiral
 */
class TestKafkaMetrics extends FunSuite {
  test("generate broker metric name correctly for kafka 1.1.0") {
    val on = KafkaMetrics.getObjectName(Kafka_1_1_0,"MessagesInPerSec",None)
    assert(on.getCanonicalName === """kafka.server:name=MessagesInPerSec,type=BrokerTopicMetrics""")
  }
  test("generate broker metric name correctly for kafka 2.1.0") {
    val on = KafkaMetrics.getObjectName(Kafka_2_1_0,"MessagesInPerSec",None)
    assert(on.getCanonicalName === """kafka.server:name=MessagesInPerSec,type=BrokerTopicMetrics""")
  }
  test("generate topic metric name correctly for kafka 1.1.0") {
    val on = KafkaMetrics.getObjectName(Kafka_1_1_0,"MessagesInPerSec",Some("topic"))
    assert(on.getCanonicalName === """kafka.server:name=MessagesInPerSec,topic=topic,type=BrokerTopicMetrics""")
  }
  test("generate topic metric name correctly for kafka 2.1.0") {
    val on = KafkaMetrics.getObjectName(Kafka_2_1_0,"MessagesInPerSec",Some("topic"))
    assert(on.getCanonicalName === """kafka.server:name=MessagesInPerSec,topic=topic,type=BrokerTopicMetrics""")
  }
}
