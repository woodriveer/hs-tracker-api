package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero

interface SaveHeroUseCase {
    fun execute(blizzardHero: BlizzardHero): BlizzardHero
}
