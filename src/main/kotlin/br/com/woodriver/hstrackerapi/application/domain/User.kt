package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort

class User(
    val id: String,
    var heroes: HashMap<String, Hero>
) {
    fun load(repositoryPort: RepositoryPort) {
        heroes = repositoryPort.findUser(id).heroes
    }

    fun markHero(repositoryPort: RepositoryPort) {
        val loadedHeroes = repositoryPort.findUser(id).heroes
        heroes.forEach {
            loadedHeroes[it.key] = Hero(it.key, it.value.completed)
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
    }

    fun unmarkHero(repositoryPort: RepositoryPort) {
        val loadedHeroes = repositoryPort.findUser(id).heroes
        heroes.forEach {
            loadedHeroes[it.key] = Hero(it.key, false)
        }
        heroes = loadedHeroes
        repositoryPort.save(this)
    }
}