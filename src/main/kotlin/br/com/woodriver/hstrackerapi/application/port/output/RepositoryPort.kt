package br.com.woodriver.hstrackerapi.application.port.output

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User

interface RepositoryPort {
    fun findUser(id: String): User
    fun save(user: User)
    fun findHero(heroId: String): Hero
    fun save(hero: Hero)
    fun findAllHeroes(): List<Hero>
}