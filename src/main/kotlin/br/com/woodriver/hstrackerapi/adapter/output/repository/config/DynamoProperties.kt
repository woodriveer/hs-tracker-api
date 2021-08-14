package br.com.woodriver.hstrackerapi.adapter.output.repository.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "aws.dynamo")
data class DynamoProperties(
        var key: String = "",
        var secret: String = ""
)