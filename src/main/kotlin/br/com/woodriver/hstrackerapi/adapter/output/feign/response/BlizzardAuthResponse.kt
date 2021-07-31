package br.com.woodriver.hstrackerapi.adapter.output.feign.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BlizzardAuthResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,
    val sub: String
)