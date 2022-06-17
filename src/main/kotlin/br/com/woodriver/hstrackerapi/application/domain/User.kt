package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.shared.logger

class User(
    val id: String,
    var heroes: HashMap<String, Hero>
) {
    fun load(repositoryPort: RepositoryPort, heroInfoPort: HeroInfoPort) {
        var isToSaveAgain = false
        heroes = repositoryPort.findUser(id).heroes
        heroes.forEach { (t, hero) ->
            if (hero.portraitURL.isEmpty()) {
                logger.info("Found a empty hero, starting to get blizzard information")
                val responseHero = heroInfoPort.getHeroInfo(hero.heroId)
                heroes[t]?.name = responseHero.name
                heroes[t]?.portraitURL = responseHero.portraitURL
                isToSaveAgain = true
                logger.info("Done to find hero [hero=${hero.heroId}] information")
            }
        }
        if (isToSaveAgain)
            repositoryPort.save(this)
    }

    fun markHero(repositoryPort: RepositoryPort) {
        val loadedHeroes = repositoryPort.findUser(id).heroes
        heroes.forEach {
            if (loadedHeroes[it.key] != null)
                loadedHeroes[it.key]?.completed = true
            else
                loadedHeroes[it.key] = Hero(
                    name = it.key,
                    completed = it.value.completed,
                    portraitURL = it.value.portraitURL,
                    heroId = it.value.heroId)
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
    }

    fun unmarkHero(repositoryPort: RepositoryPort) {
        val loadedHeroes = repositoryPort.findUser(id).heroes
        heroes.forEach {
            if(loadedHeroes[it.key] != null)
                loadedHeroes[it.key]?.completed = false
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
    }

    companion object{
        val logger = logger<User>()
    }
}