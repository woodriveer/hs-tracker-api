package br.com.woodriver.hstrackerapi.adapter.input.web.request

import com.fasterxml.jackson.annotation.JsonProperty

class HeroRequest(
    @JsonProperty(value = "hero_name")
    val heroName: String
)