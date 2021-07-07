package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.User

interface MarkHeroHasIncompleteUseCase {
    fun unmarkExecute(user: User)
}