package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.shared.logger
import org.apache.logging.log4j.util.Strings.EMPTY

data class Hero(
    var name: String = EMPTY,
    var completed: Boolean,
    var heroId: String = EMPTY,
    var portraitURL: String = EMPTY
) {

    fun loadSave(repositoryPort: RepositoryPort, heroInfoPort: HeroInfoPort) {
        var heroResult: Hero
        logger.info("Starting to find hero in database")
        try {
            heroResult = repositoryPort.findHero(heroId)
            logger.info("Done to find hero, now update values")
        } catch (ex: Exception) {
            logger.warn("Couldn't find hero in database, searching in Blizzard API...")
            heroResult = heroInfoPort.getHeroInfo(heroId)
            logger.info("Done to find [hero=${heroResult.name}] in Blizzard API")
            repositoryPort.save(heroResult)
            logger.info("Done to save new hero in database...")
        }
        portraitURL = heroResult.portraitURL
        heroId = heroResult.heroId
        logger.info("Done to update portraitURL and [heroId=${heroResult}]")
    }

    companion object {
        val logger = logger<Hero>()
    }
}