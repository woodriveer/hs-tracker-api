package br.com.woodriver.hstrackerapi.application.service

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.application.port.output.HeroInfoPort
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import br.com.woodriver.hstrackerapi.utils.randomObject
import br.com.woodriver.hstrackerapi.utils.randomUserWithEmptyPortrait
import org.apache.logging.log4j.util.Strings
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class UserServiceTest {
    private val repositoryPort: RepositoryPort = mock()
    private val heroInfoPort: HeroInfoPort = mock()

    private val userService = UserService(repositoryPort, heroInfoPort)

    @Test
    fun `Should load user info with portrait information filled`() {
        val user = randomObject<User>()
        whenever(repositoryPort.findUser(any())).thenReturn(user)
        whenever(heroInfoPort.getHeroInfo(any())).thenReturn(randomObject())

        userService.execute(user)

        verify(repositoryPort, times(1)).findUser(any())
        verify(heroInfoPort, times(0)).getHeroInfo(any())
    }

    @Test
    fun `Should load user info with portrait information empty`() {
        val user = randomUserWithEmptyPortrait(1)
        whenever(repositoryPort.findUser(any())).thenReturn(user)
        whenever(heroInfoPort.getHeroInfo(any())).thenReturn(randomObject())

        userService.execute(user)

        verify(repositoryPort, times(1)).findUser(any())
        verify(heroInfoPort, times(1)).getHeroInfo(any())
    }

    @Test
    fun `Should load user info with portrait information empty of n heroes`() {
        val user = randomUserWithEmptyPortrait(15)
        val hero = Hero(
            name = "",
            completed = false,
            heroId = "",
            portraitURL = ""
        )
        whenever(repositoryPort.findUser(any())).thenReturn(user)
        whenever(heroInfoPort.getHeroInfo(any())).thenReturn(hero)

        userService.execute(user)

        verify(repositoryPort, times(1)).findUser(any())
        verify(heroInfoPort, times(15)).getHeroInfo(any())
    }

    @Test
    fun `Should mark hero with true and create item in hash`() {
        val user = randomUserWithEmptyPortrait(1)
        whenever(repositoryPort.findUser(any())).thenReturn(randomObject())

        userService.markExecute(user)
    }

    @Test
    fun `Should mark hero with true and but just update field`() {
        val user = randomUserWithEmptyPortrait(1)
        whenever(repositoryPort.findUser(any())).thenReturn(user)

        userService.markExecute(user)
    }

    @Test
    fun `Should unmark hero`() {
        val user = randomUserWithEmptyPortrait(1)
        whenever(repositoryPort.findUser(any())).thenReturn(user)

        userService.unmarkExecute(user)
    }
}