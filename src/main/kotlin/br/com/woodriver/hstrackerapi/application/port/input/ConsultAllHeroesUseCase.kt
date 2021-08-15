package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero

interface ConsultAllHeroesUseCase{
    fun execute(): List<BlizzardHero>
}