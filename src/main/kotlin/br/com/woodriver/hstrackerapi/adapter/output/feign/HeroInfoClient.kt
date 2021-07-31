package br.com.woodriver.hstrackerapi.adapter.output.feign

import br.com.woodriver.hstrackerapi.adapter.input.web.helper.toDomain
import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardAPI
import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardAuthClient
import br.com.woodriver.hstrackerapi.adapter.output.feign.config.BlizzardAuthConfiguration.Companion.CLIENT_CREDENTIALS
import br.com.woodriver.hstrackerapi.adapter.output.feign.config.BlizzardAuthConfiguration.Companion.GRANT_TYPE
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import org.springframework.stereotype.Component

@Component
class HeroInfoClient(
    val blizzardAuthClient: BlizzardAuthClient,
    val blizzardAPI: BlizzardAPI
): HeroInfoPort {
    override fun getHeroInfo(heroId: String): Hero {
        val authToken = blizzardAuthClient.getToken(mapOf(Pair(GRANT_TYPE, CLIENT_CREDENTIALS)))
        val response = blizzardAPI.getHeroInformation(heroId, "Bearer ${authToken.accessToken}")
        return response.toDomain()
    }
}