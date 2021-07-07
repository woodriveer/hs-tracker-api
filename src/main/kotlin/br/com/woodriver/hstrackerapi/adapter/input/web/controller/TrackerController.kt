package br.com.woodriver.hstrackerapi.adapter.input.web.controller

import br.com.woodriver.hstrackerapi.adapter.input.web.api.TrackerAPI
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toDomain
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.CompletedHeroesResponse
import br.com.woodriver.hstrackerapi.application.port.input.ConsultCompletedHeroesUseCase
import br.com.woodriver.hstrackerapi.application.port.input.MarkHeroHasCompletedUseCase
import br.com.woodriver.hstrackerapi.application.port.input.MarkHeroHasIncompleteUseCase
import br.com.woodriver.hstrackerapi.shared.logger
import br.com.woodriver.hstrackerapi.shared.objectToJson
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Component
@RestController
@RequestMapping(value = ["/trackers"])
class TrackerController(
    val consultCompletedHeroesUseCase: ConsultCompletedHeroesUseCase,
    val markHeroHasCompletedUseCase: MarkHeroHasCompletedUseCase,
    val markHeroHasIncompleteUseCase: MarkHeroHasIncompleteUseCase
): TrackerAPI {
    private val logger = logger<TrackerController>()

    override fun completedHeroes(authorization: String): CompletedHeroesResponse {
        logger.info("Starting to find completed heroes")
        val result = consultCompletedHeroesUseCase.execute(authorization.toDomain()).apply {
            logger.info("Done to consult heroes from [{}]", kv("userId", id))
        }
        return result.toResponse()
    }

    override fun markCompletenessHero(authorization: String, heroRequest: HeroRequest) {
        logger.info("Starting to mark Hero=${heroRequest.heroName} has completed.")
        markHeroHasCompletedUseCase.markExecute(heroRequest.toDomain(authorization)).apply {
            logger.info("Done to mark hero has completed. [{}]", kv("User", objectToJson()))
        }
    }

    override fun revertMarkCompletenessHero(authorization: String, heroRequest: HeroRequest) {
        logger.info("Starting to mark Hero=${heroRequest.heroName} has incomplete.")
        markHeroHasIncompleteUseCase.unmarkExecute(heroRequest.toDomain(authorization)).apply {
            logger.info("Done to mark hero has incomplete. [{}]", kv("User", objectToJson()))
        }
    }

    companion object {
        const val AUTH_HEADER = "x-hstracker-authorization"
    }
}