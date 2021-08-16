package br.com.woodriver.hstrackerapi.adapter.input.web.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class HeroResponse(
    val heroName: String,
    val heroId: String,
    val imageURL: String
)