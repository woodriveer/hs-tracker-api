package br.com.woodriver.hstrackerapi.adapter.output.feign.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BlizzardTokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val sub: String
)