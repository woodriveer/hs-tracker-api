package br.com.woodriver.hstrackerapi.adapter.output.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.apache.logging.log4j.util.Strings

@DynamoDBTable(tableName = "HsTrackerHeroes")
open class HeroEntity(
    @DynamoDBHashKey(attributeName = "HeroId")
    var heroId: String = Strings.EMPTY,
    @DynamoDBAttribute(attributeName = "HeroName")
    var heroName: String = Strings.EMPTY,
    @DynamoDBAttribute(attributeName = "PortraitURL")
    var portraitURL: String = Strings.EMPTY
)