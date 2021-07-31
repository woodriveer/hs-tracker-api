package br.com.woodriver.hstrackerapi.application.port.output

import br.com.woodriver.hstrackerapi.application.domain.Hero

interface HeroInfoPort {
    fun getHeroInfo(heroId: String): Hero
}