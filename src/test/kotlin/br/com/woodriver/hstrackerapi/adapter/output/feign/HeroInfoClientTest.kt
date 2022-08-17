package br.com.woodriver.hstrackerapi.adapter.output.feign

import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardAPI
import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardAuthClient
import br.com.woodriver.hstrackerapi.utils.randomObject
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


internal class HeroInfoClientTest {
    private val blizzardAuthClient: BlizzardAuthClient = mock()
    private val blizzardAPI: BlizzardAPI = mock()

    private val heroInfoClient = HeroInfoClient(blizzardAuthClient, blizzardAPI)

    @Test
    fun getHeroInfo() {
        val hero = "hue"

        whenever(blizzardAuthClient.getToken(any())).thenReturn(randomObject())
        whenever(blizzardAPI.getHeroInformation(any(), any())).thenReturn(randomObject())

        heroInfoClient.getHeroInfo(hero)
    }
}