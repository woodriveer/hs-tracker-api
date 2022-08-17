package br.com.woodriver.hstrackerapi.adapter.output.repository

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.utils.randomObject
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList
import com.amazonaws.services.kms.model.NotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import kotlin.test.assertEquals


internal class DynamoRepositoryTest {

    private val mapper: DynamoDBMapper = mock()
    private val dynamoRepository = DynamoRepository(mapper)

    @Test
    fun findUser() {
        val user = "yan"
        val listMock: PaginatedQueryList<UserEntity> = mock()

        whenever(listMock.first()).thenReturn(UserEntity("123", "123", "{}"))
        whenever(mapper.query(eq(UserEntity::class.java), any())).thenReturn(
            listMock
        )

        val response = dynamoRepository.findUser(user)

        assertEquals("123", response.id)
    }


    @Test
    fun notFoundUser() {
        val user = "yan"
        val listMock: PaginatedQueryList<UserEntity> = mock()

        whenever(listMock.isEmpty()).thenReturn(true)
        whenever(mapper.query(eq(UserEntity::class.java), any())).thenReturn(
            listMock
        )
        val response = dynamoRepository.findUser(user)

        assertEquals(user, response.id)
    }

    @Test
    fun save() {
        val user: User = randomObject()

        doNothing().whenever(mapper).save(any<UserEntity>())

        dynamoRepository.save(user)

    }

    @Test
    fun testSave() {
        val hero: Hero = randomObject()

        doNothing().whenever(mapper).save(any<HeroEntity>())

        dynamoRepository.save(hero)
    }

    @Test
    fun findHero() {
        val hero = "yan"
        val listMock: PaginatedQueryList<HeroEntity> = mock()

        whenever(listMock.first()).thenReturn(HeroEntity("123", "123", "{}"))
        whenever(mapper.query(eq(HeroEntity::class.java), any())).thenReturn(
            listMock
        )

        val response = dynamoRepository.findHero(hero)

        assertEquals("123", response.heroId)
    }

    @Test
    fun notFoundHero() {
        val hero = "yan"
        val listMock: PaginatedQueryList<HeroEntity> = mock()

        whenever(listMock.isEmpty()).thenReturn(true)
        whenever(mapper.query(eq(HeroEntity::class.java), any())).thenReturn(
            listMock
        )
        assertThrows<NotFoundException> { dynamoRepository.findHero(hero) }
    }

    @Test
    fun findAllHeroes() {
        val heroList = arrayListOf<HeroEntity>()
        heroList.add(randomObject())
        val listMock: PaginatedScanList<HeroEntity> = mock()

        whenever(listMock.iterator()).thenReturn(heroList.iterator())
        whenever(mapper.scan(eq(HeroEntity::class.java), any())).thenReturn(
            listMock
        )

        val response = dynamoRepository.findAllHeroes()
    }
}