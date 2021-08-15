package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.apache.logging.log4j.util.Strings.EMPTY

data class BlizzardHero(
        var heroId: String = EMPTY,
        var name: String = EMPTY,
        var imageUrl: String = EMPTY
) {

    fun save(repositoryPort: RepositoryPort){
        repositoryPort.save(this)
    }

    companion object {
        fun consultAllHeroes(repositoryPort: RepositoryPort): List<BlizzardHero> {
            return repositoryPort.findAllHeroes()
        }
    }
}