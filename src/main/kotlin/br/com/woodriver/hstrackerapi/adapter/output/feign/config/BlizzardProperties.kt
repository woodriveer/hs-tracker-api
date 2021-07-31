package br.com.woodriver.hstrackerapi.adapter.output.feign.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "blizzard.api")
class BlizzardProperties(
    val clientSecret: String,
    val clientId: String,
    val urlAuth: String,
    val url: String
)