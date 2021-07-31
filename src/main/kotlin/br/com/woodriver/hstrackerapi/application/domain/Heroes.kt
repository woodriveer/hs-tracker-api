package br.com.woodriver.hstrackerapi.application.domain

import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort

data class Heroes(
    var list: List<Hero> = arrayListOf()
) {

    fun getAllHeroes(repository: RepositoryPort) {
        list = repository.findAllHeroes()
    }
}