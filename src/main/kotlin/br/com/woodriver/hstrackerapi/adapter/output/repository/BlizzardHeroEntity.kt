package br.com.woodriver.hstrackerapi.adapter.output.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.apache.logging.log4j.util.Strings.EMPTY

@DynamoDBTable(tableName = "HsHeroes")
open class BlizzardHeroEntity(
        @DynamoDBHashKey(attributeName = "UserId")
        var userId: String = EMPTY,
        @DynamoDBAttribute(attributeName = "Name")
        var name: String = EMPTY,
        @DynamoDBAttribute(attributeName = "ImageUrl")
        var imageUrl: String = EMPTY
) {
        companion object {
                const val HERO_INFO = "hero-info"
        }
}