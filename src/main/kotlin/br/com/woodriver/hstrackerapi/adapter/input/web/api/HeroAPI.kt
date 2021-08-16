package br.com.woodriver.hstrackerapi.adapter.input.web.api

import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroIdRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.HeroResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.response.HeroesResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface HeroAPI {
    @GetMapping(name = "Get all heroes")
    fun getAllHeroes(): List<HeroesResponse>

    @PostMapping(name = "Save a hero by blizzard id")
    fun save(
        @RequestBody heroIdRequest: HeroIdRequest
    ): HeroResponse
}