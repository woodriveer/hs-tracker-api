package br.com.woodriver.hstrackerapi.adapter.input.web.api

import br.com.woodriver.hstrackerapi.adapter.input.web.controller.TrackerController.Companion.AUTH_HEADER
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.CompletedHeroesResponse
import org.apache.http.auth.AUTH
import org.springframework.web.bind.annotation.*

interface TrackerAPI {
    @GetMapping(name = "Get all completed heroes")
    fun completedHeroes(
        @RequestHeader(value = AUTH_HEADER) authorization: String
    ): CompletedHeroesResponse

    @PostMapping(name = "Mark specific hero as completed")
    fun markCompletenessHero(
        @RequestHeader(value = AUTH_HEADER) authorization: String,
        @RequestBody heroRequest: HeroRequest
    )

    @PutMapping(name = "Removed a specific hero from completeness")
    fun revertMarkCompletenessHero(
        @RequestHeader(value = AUTH_HEADER) authorization: String,
        @RequestBody heroRequest: HeroRequest
    )
}