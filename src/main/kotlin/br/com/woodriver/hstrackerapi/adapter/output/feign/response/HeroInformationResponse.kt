package br.com.woodriver.hstrackerapi.adapter.output.feign.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HeroInformationResponse(
    val slug: String,
    val name: String,
    val battlegrounds: Battlegrounds
) {
    data class Battlegrounds(
        val hero: Boolean,
        val imageGold: String
    )
}