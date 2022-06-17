package br.com.woodriver.hstrackerapi.adapter.input.web.request

import com.fasterxml.jackson.annotation.JsonProperty

data class HeroResponse(
    val heroId: String,
    @JsonProperty("hero_name")
    val heroName: String,
    @JsonProperty("portrait_url")
    val portraitURL: String
)