package br.com.woodriver.hstrackerapi.adapter.output.repository.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions.US_EAST_1
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonDynamoConfig {

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration("http://localhost:4566", US_EAST_1.name)
            ).build()
    }
}