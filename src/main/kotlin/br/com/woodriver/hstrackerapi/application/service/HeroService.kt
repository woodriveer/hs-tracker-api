package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.Heroes
import br.com.woodriver.hstrackerapi.application.port.input.GetAllHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.HeroInfoByNameUseCase
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.springframework.stereotype.Service

@Service
class HeroService(
    val repositoryPort: RepositoryPort,
    val heroInfoPort: HeroInfoPort
): GetAllHeroesUseCase, HeroInfoByNameUseCase {
    override fun execute(heroes: Heroes): List<Hero> {
        heroes.getAllHeroes(repositoryPort)
        return heroes.list
    }

    override fun execute(hero: Hero): Hero {
        return hero.apply {
            `load & save`(repositoryPort, heroInfoPort)
        }
    }
}