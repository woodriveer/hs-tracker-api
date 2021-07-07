package br.com.woodriver.hstrackerapi.application.port.output

import br.com.woodriver.hstrackerapi.application.domain.User

interface RepositoryPort {
    fun findUser(id: String): User
    fun save(user: User)
}