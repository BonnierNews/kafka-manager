/**
 * Copyright 2015 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */

package kafka.manager.utils

import java.util.Properties

import kafka.manager.model._

trait LogkafkaNewConfigs {
  def configNames : Set[String]
  def configMaps: Map[String, String]
  def validate(props: Properties)
}

object LogkafkaNewConfigs {
  
  val logkafkaConfigsByVersion : Map[KafkaVersion, LogkafkaNewConfigs] = Map(
    Kafka_1_1_0 -> logkafka82.LogConfig,
    Kafka_2_0_0 -> logkafka82.LogConfig,
    Kafka_2_1_0 -> logkafka82.LogConfig
    )

  def configNames(version: KafkaVersion) : Set[String] = {
    logkafkaConfigsByVersion.get(version) match {
      case Some(tc) => tc.configNames
      case None => throw new IllegalArgumentException(s"Undefined logkafka configs for version : $version, cannot get config names")
    }
  }
  def configMaps(version: KafkaVersion) : Map[String, String] = {
    logkafkaConfigsByVersion.get(version) match {
      case Some(tc) => tc.configMaps
      case None => throw new IllegalArgumentException(s"Undefined logkafka configs for version : $version, cannot get config maps")
    }
  }
  def validate(version: KafkaVersion, props: Properties) : Unit = {
    logkafkaConfigsByVersion.get(version) match {
      case Some(tc) => tc.validate(props)
      case None => throw new IllegalArgumentException(s"Undefined logkafka configs for version : $version, cannot validate config")
    }
  }
}
