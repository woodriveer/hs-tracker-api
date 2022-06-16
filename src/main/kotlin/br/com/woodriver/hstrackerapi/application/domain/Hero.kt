package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.apache.logging.log4j.util.Strings.EMPTY

class Hero(
    var name: String = EMPTY,
    var completed: Boolean,
    var heroId: String = EMPTY,
    var portraitURL: String = EMPTY
) {

    fun `load & save`(repositoryPort: RepositoryPort, heroInfoPort: HeroInfoPort) {
        var heroResult: Hero
        try {
            heroResult = repositoryPort.findHero(heroId)
        } catch (ex: Exception) {
            heroResult = heroInfoPort.getHeroInfo(heroId)
            repositoryPort.save(heroResult)
        }
        portraitURL = heroResult.portraitURL
        heroId = heroResult.heroId
    }
}