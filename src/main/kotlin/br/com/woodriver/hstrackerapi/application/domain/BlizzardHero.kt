package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.HeroesInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.apache.logging.log4j.util.Strings.EMPTY

data class BlizzardHero(
        val heroId: String = EMPTY,
        var name: String = EMPTY,
        var imageURL: String = EMPTY
) {

    fun save(repositoryPort: RepositoryPort, heroesInfoPort: HeroesInfoPort): BlizzardHero{
        val heroWithInfo = heroesInfoPort.getHeroOtherInfo(heroId)
        name = heroWithInfo.name
        imageURL = heroWithInfo.imageURL
        repositoryPort.save(this)

        return this
    }

    companion object {
        fun consultAllHeroes(repositoryPort: RepositoryPort): List<BlizzardHero> {
            return repositoryPort.findAllHeroes()
        }
    }
}