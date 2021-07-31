package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.application.port.input.ConsultCompletedHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.MarkHeroHasCompletedUseCase
import br.com.woodriver.hstrackerapi.application.port.input.MarkHeroHasIncompleteUseCase
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import org.springframework.stereotype.Service

@Service
class UserService(val repositoryPort: RepositoryPort
): ConsultCompletedHeroesUseCase, MarkHeroHasCompletedUseCase, MarkHeroHasIncompleteUseCase
{
    override fun execute(user: User): User {
        return user.apply {
            load(repositoryPort)
        }
    }

    override fun markExecute(user: User) {
        user.apply {
            markHero(repositoryPort)
        }
    }

    override fun unmarkExecute(user: User) {
        user.apply {
            unmarkHero(repositoryPort)
        }
    }
}