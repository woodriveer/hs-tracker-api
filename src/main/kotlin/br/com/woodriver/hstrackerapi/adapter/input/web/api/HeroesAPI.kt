package br.com.woodriver.hstrackerapi.adapter.input.web.api

import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroesResponse
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

interface HeroesAPI {

    @GetMapping(value = ["/{heroName}"])
    fun getHeroInfo(@PathVariable heroName: String): HeroResponse

    @GetMapping
    fun getAllHeroes(): HeroesResponse
}