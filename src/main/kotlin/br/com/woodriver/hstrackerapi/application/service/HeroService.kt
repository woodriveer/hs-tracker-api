package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.Heroes
import br.com.woodriver.hstrackerapi.application.port.input.GetAllHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.HeroInfoByNameUseCase
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.shared.logger
import org.springframework.stereotype.Service

@Service
class HeroService(
    val repositoryPort: RepositoryPort,
    val heroInfoPort: HeroInfoPort
): GetAllHeroesUseCase, HeroInfoByNameUseCase {
    override fun execute(heroes: Heroes): List<Hero> {
        logger.info("Starting to get all information about heroes...")
        heroes.getAllHeroes(repositoryPort)
        return heroes.list.apply {
            logger.info("Done to find ${heroes.list.size} heroes")
        }
    }

    override fun execute(hero: Hero): Hero {
        logger.info("Starting to save [hero=${hero.name}] information...")
        return hero.apply {
            loadSave(repositoryPort, heroInfoPort)
            logger.info("Done to save hero information!")
        }
    }

    companion object {
        val logger = logger<HeroService>()
    }
}