package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.Heroes

interface GetAllHeroesUseCase {
    fun execute(heroes: Heroes): List<Hero>
}