package br.com.woodriver.hstrackerapi.adapter.input.web.controller

import br.com.woodriver.hstrackerapi.adapter.input.web.api.HeroAPI
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toDomainBlizzardHero
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.HeroesResponse
import br.com.woodriver.hstrackerapi.application.port.input.ConsultAllHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.SaveHeroUseCase
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
@RequestMapping(value = ["/heroes"])
class HeroController(
        val consultAllHeroesUseCase: ConsultAllHeroesUseCase,
        val saveHeroUseCase: SaveHeroUseCase
): HeroAPI {
    override fun getAllHeroes(): List<HeroesResponse> {
        return consultAllHeroesUseCase.execute().toResponse()
    }

    override fun save(heroRequest: HeroRequest) {
        saveHeroUseCase.execute(heroRequest.toDomainBlizzardHero())
    }

}