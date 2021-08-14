package br.com.woodriver.hstrackerapi.adapter.output.repository

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.apache.logging.log4j.util.Strings

@DynamoDBTable(tableName = "HsTracker")
open class UserEntity(
    @DynamoDBHashKey(attributeName = "UserId")
    var userId: String = Strings.EMPTY,
    @DynamoDBRangeKey(attributeName = "Type")
    var type: String = Strings.EMPTY,
    @DynamoDBAttribute(attributeName = "Heroes")
    var heroes: String = "{}"
) {
    companion object {
        const val USER_INFO = "user-info"
    }
}