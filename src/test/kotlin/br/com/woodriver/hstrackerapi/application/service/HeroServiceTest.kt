package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.Heroes
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.utils.randomObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class HeroServiceTest {

    private val repositoryPort: RepositoryPort = mock()
    private val heroInfoPort: HeroInfoPort = mock()

    private val heroService = HeroService(repositoryPort, heroInfoPort)

    @BeforeEach
    fun setup() {
        whenever(repositoryPort.findAllHeroes()).thenReturn(randomObject())
    }

    @Test
    fun `Should return all heroes saved in database`() {
        val notEmptyList = arrayListOf<Hero>()
        notEmptyList.add(randomObject())
        notEmptyList.add(randomObject())

        whenever(repositoryPort.findAllHeroes()).thenReturn(notEmptyList)

        val response = heroService.execute(Heroes(
            arrayListOf(randomObject())
        ))

        assertEquals(2, response.size)
    }

    @Test
    fun `Should save hero status for a single player when hero is already in database`() {
        val hero: Hero = randomObject()
        whenever(repositoryPort.findHero(any())).thenReturn(hero)

        val response = heroService.execute(hero)

        assertEquals(hero.name, response.name)

    }

    @Test
    fun `Should save hero status for a single player when hero isn't in database`() {
        val hero: Hero = randomObject()
        whenever(repositoryPort.findHero(any())).thenThrow(java.lang.RuntimeException("Couldn't load hero information"))
        whenever(heroInfoPort.getHeroInfo(any())).thenReturn(hero)

        val response = heroService.execute(hero)

        assertEquals(hero.name, response.name)

    }
}