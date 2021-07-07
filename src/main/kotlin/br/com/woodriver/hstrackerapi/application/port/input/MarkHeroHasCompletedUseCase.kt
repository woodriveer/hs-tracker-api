package br.com.woodriver.hstrackerapi.application.port.input

import br.com.woodriver.hstrackerapi.application.domain.User

interface MarkHeroHasCompletedUseCase {
    fun markExecute(user: User)
}