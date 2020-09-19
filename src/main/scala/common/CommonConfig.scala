
package common

import com.typesafe.config.{Config, ConfigFactory}

object CommonConfig{
    private val cfg = ConfigFactory.load()
    val env: String = if (cfg.hasPath("env")) cfg.getString("env") else "default"
    val config: Config = cfg
        .getConfig(env)
        .withFallback(cfg.getConfig("default"))

    val kafka_user: String = config.getString("user")
    val kafka_topic: String = config.getString("topic")
    val kafka_consumer: String = config.getString("consumer")

}