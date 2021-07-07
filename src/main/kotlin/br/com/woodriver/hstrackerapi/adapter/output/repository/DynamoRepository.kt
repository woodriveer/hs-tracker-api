package br.com.woodriver.hstrackerapi.adapter.output.repository

import br.com.woodriver.hstrackerapi.adapter.output.repository.UserEntity.Companion.USER_INFO
import br.com.woodriver.hstrackerapi.adapter.output.repository.helper.toDomain
import br.com.woodriver.hstrackerapi.adapter.output.repository.helper.toEntity
import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.application.port.output.RepositoryPort
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import org.springframework.stereotype.Repository

@Repository
class DynamoRepository(val amazonDynamoDB: AmazonDynamoDB): RepositoryPort {
    override fun findUser(id: String): User {
        val entity = UserEntity(userId = id, type = USER_INFO)
        val mapper = DynamoDBMapper(amazonDynamoDB)
        val query = DynamoDBQueryExpression<UserEntity>()
            .withHashKeyValues(entity)

        val result = mapper.query(UserEntity::class.java, query)
        return result.first().toDomain()
    }

    override fun save(user: User) {
        val mapper = DynamoDBMapper(amazonDynamoDB)
        mapper.save(user.toEntity())
    }
}