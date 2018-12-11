/**
 * Copyright 2015 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */
package kafka.manager.utils

import kafka.manager.model.ClusterConfig
import org.scalatest.FunSuite
import org.scalatest.Matchers

/**
 * @author hiral
 */
class TestClusterConfig extends FunSuite with Matchers {

  test("invalid name") {
    intercept[IllegalArgumentException] {
      ClusterConfig("qa!","2.1.0","localhost",jmxEnabled = false, pollConsumers = true, filterConsumers = true, jmxUser = None, jmxPass = None, jmxSsl = false, tuning = None, securityProtocol = "PLAINTEXT", saslMechanism = None, jaasConfig = None)
    }
  }

  test("invalid kafka version") {
    intercept[IllegalArgumentException] {
      ClusterConfig("qa","0.8.1","localhost:2181",jmxEnabled = false, pollConsumers = true, filterConsumers = true, jmxUser = None, jmxPass = None, jmxSsl = false, tuning = None, securityProtocol = "PLAINTEXT", saslMechanism = None, jaasConfig = None)
    }
  }

  test("serialize and deserialize 1.1.0") {
    val cc = ClusterConfig("qa", "1.1.0", "localhost:2181", jmxEnabled = false, pollConsumers = true, filterConsumers = true, activeOffsetCacheEnabled = true, jmxUser = None, jmxPass = None, jmxSsl = false, tuning = None, securityProtocol = "SASL_PLAINTEXT", saslMechanism = Option("PLAIN"), jaasConfig = Option("blah"))
    val serialize: String = ClusterConfig.serialize(cc)
    val deserialize = ClusterConfig.deserialize(serialize)
    assert(deserialize.isSuccess === true)
    assert(cc == deserialize.get)
  }

  test("serialize and deserialize 2.0.0") {
    val cc = ClusterConfig("qa", "2.0.0", "localhost:2181", jmxEnabled = false, pollConsumers = true, filterConsumers = true, activeOffsetCacheEnabled = true, jmxUser = None, jmxPass = None, jmxSsl = false, tuning = None, securityProtocol = "SASL_PLAINTEXT", saslMechanism = Option("PLAIN"), jaasConfig = Option("blah"))
    val serialize: String = ClusterConfig.serialize(cc)
    val deserialize = ClusterConfig.deserialize(serialize)
    assert(deserialize.isSuccess === true)
    assert(cc == deserialize.get)
  }

  test("serialize and deserialize 2.1.0") {
    val cc = ClusterConfig("qa", "2.1.0", "localhost:2181", jmxEnabled = false, pollConsumers = true, filterConsumers = true, activeOffsetCacheEnabled = true, jmxUser = None, jmxPass = None, jmxSsl = false, tuning = None, securityProtocol = "SASL_PLAINTEXT", saslMechanism = Option("PLAIN"), jaasConfig = Option("blah"))
    val serialize: String = ClusterConfig.serialize(cc)
    val deserialize = ClusterConfig.deserialize(serialize)
    assert(deserialize.isSuccess === true)
    assert(cc == deserialize.get)
  }


}
