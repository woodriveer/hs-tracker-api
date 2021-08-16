package br.com.woodriver.hstrackerapi.adapter.input.web.request

import com.fasterxml.jackson.annotation.JsonProperty

class HeroIdRequest(
    @JsonProperty(value = "hero_id")
    val heroId: String
)