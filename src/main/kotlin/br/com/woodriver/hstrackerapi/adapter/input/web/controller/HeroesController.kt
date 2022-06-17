package br.com.woodriver.hstrackerapi.adapter.input.web.controller

import br.com.woodriver.hstrackerapi.adapter.input.web.api.HeroesAPI
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toHeroDomain
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroesResponse
import br.com.woodriver.hstrackerapi.application.domain.Heroes
import br.com.woodriver.hstrackerapi.application.port.input.GetAllHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.HeroInfoByNameUseCase
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
@RequestMapping(value = ["/heroes"])
class HeroesController(
    private val getAllHeroesUseCase: GetAllHeroesUseCase,
    private val heroInfoByNameUseCase: HeroInfoByNameUseCase
): HeroesAPI {
    override fun getHeroInfo(heroName: String): HeroResponse {
        val result = heroInfoByNameUseCase.execute(heroName.toHeroDomain())
        return result.toResponse()
    }

    override fun getAllHeroes(): HeroesResponse {
        val result = getAllHeroesUseCase.execute(Heroes(arrayListOf()))
        return result.toResponse()
    }
}