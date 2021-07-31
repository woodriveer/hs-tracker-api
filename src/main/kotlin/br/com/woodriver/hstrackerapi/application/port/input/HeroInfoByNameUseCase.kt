package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.Hero

interface HeroInfoByNameUseCase {
    fun execute(hero: Hero): Hero
}