package br.com.woodriver.hstrackerapi.adapter.output.repository.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions.SA_EAST_1
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonDynamoConfig(val dynamoProperties: DynamoProperties) {

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(SA_EAST_1)
                .withCredentials(AWSStaticCredentialsProvider(
                        BasicAWSCredentials(dynamoProperties.key, dynamoProperties.secret)
                ))
                .build()
    }
}