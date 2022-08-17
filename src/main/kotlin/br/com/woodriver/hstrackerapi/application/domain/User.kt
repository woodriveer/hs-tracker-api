package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.shared.logger
import net.logstash.logback.argument.StructuredArguments.kv

data class User(
    val id: String,
    var heroes: HashMap<String, Hero>
) {
    fun load(repositoryPort: RepositoryPort, heroInfoPort: HeroInfoPort) {
        logger.info("Starting to load [${kv("user", id)}] information...")
        var isToSaveAgain = false
        var portraitChanged = 0
        heroes = repositoryPort.findUser(id).heroes
        logger.info("Loaded ${heroes.size} heroes")
        heroes.forEach { (t, hero) ->
            if (hero.portraitURL.isEmpty()) {
                logger.info("Found a empty hero, starting to get blizzard information")
                val responseHero = heroInfoPort.getHeroInfo(hero.heroId)
                heroes[t]?.name = responseHero.name
                heroes[t]?.portraitURL = responseHero.portraitURL
                isToSaveAgain = true
                portraitChanged++
                logger.info("Done to find hero [hero=${hero.heroId}] information")
            }
        }
        if (isToSaveAgain) {
            logger.info("Saving new information's with portrait info in $portraitChanged")
            repositoryPort.save(this)
        }
        logger.info("Done to load [${kv("user", id)}] information successfully")
    }

    fun markHero(repositoryPort: RepositoryPort) {
        logger.info("Starting to mark [${kv("hero", heroes[heroes.keys.first()]!!.heroId)}] for [${kv("user", id)}]")
        val loadedHeroes = repositoryPort.findUser(id).heroes
        logger.info("Loaded ${heroes.size} others heroes")
        heroes.forEach {
            logger.info("Checking if [hero=${it.value.heroId}] is already marked...")
            if (loadedHeroes[it.key] != null) {
                loadedHeroes[it.key]?.completed = true
                logger.info("[hero=${it.value.heroId}] exist, marking with true!")
            } else
                logger.debug("[hero=${it.value.heroId}] not exist, creating hero with mark true")
                loadedHeroes[it.key] = Hero(
                    name = it.key,
                    completed = it.value.completed,
                    portraitURL = it.value.portraitURL,
                    heroId = it.value.heroId)
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
        logger.info("Done to mark hero for [${kv("user", id)}] successfully")
    }

    fun unmarkHero(repositoryPort: RepositoryPort) {
        logger.info("Starting to un-mark [${kv("hero", heroes[heroes.keys.first()]!!.heroId)}] for [${kv("user", id)}]")
        val loadedHeroes = repositoryPort.findUser(id).heroes
        logger.info("Loaded ${heroes.size} others heroes")
        heroes.forEach {
            if(loadedHeroes[it.key] != null)
                loadedHeroes[it.key]?.completed = false
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
        logger.info("Done to un-mark hero for [${kv("user", id)}] successfully")
    }

    companion object{
        val logger = logger<User>()
    }
}