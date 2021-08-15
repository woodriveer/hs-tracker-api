package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero
import br.com.woodriver.hstrackerapi.application.port.input.ConsultAllHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.SaveHeroUseCase
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.springframework.stereotype.Service

@Service
class HeroService(val repositoryPort: RepositoryPort): ConsultAllHeroesUseCase, SaveHeroUseCase {

    override fun execute(blizzardHero: BlizzardHero) {
        blizzardHero.save(repositoryPort)
    }

    override fun execute(): List<BlizzardHero> {
        return BlizzardHero.consultAllHeroes(repositoryPort)
    }

}