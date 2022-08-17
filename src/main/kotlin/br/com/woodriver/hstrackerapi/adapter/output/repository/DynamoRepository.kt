package br.com.woodriver.hstrackerapi.adapter.output.repository

import br.com.woodriver.hstrackerapi.adapter.output.repository.UserEntity.Companion.USER_INFO
import br.com.woodriver.hstrackerapi.adapter.output.repository.helper.toDomain
import br.com.woodriver.hstrackerapi.adapter.output.repository.helper.toEntity
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import com.amazonaws.services.kms.model.NotFoundException
import org.springframework.stereotype.Repository

@Repository
class DynamoRepository(private val mapper: DynamoDBMapper): RepositoryPort {
    override fun findUser(id: String): User {
        var returnUser = User(id = id, heroes = hashMapOf())
        val entity = UserEntity(userId = id, type = USER_INFO)
        val query = DynamoDBQueryExpression<UserEntity>()
            .withHashKeyValues(entity)

        val result = mapper.query(UserEntity::class.java, query)
        if (!result.isEmpty())
            returnUser = result.first().toDomain()
        return returnUser
    }

    override fun save(user: User) {
        mapper.save(user.toEntity())
    }

    override fun save(hero: Hero) {
        mapper.save(hero.toEntity())
    }

    override fun findHero(heroId: String): Hero {
        val returnHero: Hero
        val entity = HeroEntity(heroId = heroId)
        val query = DynamoDBQueryExpression<HeroEntity>()
            .withHashKeyValues(entity)

        val result = mapper.query(HeroEntity::class.java, query)
        if (!result.isEmpty())
            returnHero = result.first().toDomain()
        else
            throw NotFoundException("Could not find [Hero=$heroId]")
        return returnHero
    }

    override fun findAllHeroes(): List<Hero> {
        val heroes = arrayListOf<Hero>()

        val list = mapper.scan(HeroEntity::class.java, DynamoDBScanExpression())
        list.forEach {
            heroes.add(it.toDomain())
        }

        return heroes
    }
}