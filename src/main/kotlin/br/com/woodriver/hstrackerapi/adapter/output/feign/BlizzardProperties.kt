package br.com.woodriver.hstrackerapi.adapter.output.feign

import org.apache.logging.log4j.util.Strings
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "blizzard.api")
data class BlizzardProperties(
    var key: String = Strings.EMPTY,
    var secret: String = Strings.EMPTY
)