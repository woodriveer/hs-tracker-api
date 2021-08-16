package br.com.woodriver.hstrackerapi.application.port.output

import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero

interface HeroesInfoPort {
    fun getHeroOtherInfo(heroId: String): BlizzardHero
}