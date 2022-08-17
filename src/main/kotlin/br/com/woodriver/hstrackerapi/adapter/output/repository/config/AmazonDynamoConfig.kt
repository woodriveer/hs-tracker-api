package br.com.woodriver.hstrackerapi.adapter.output.repository.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.regions.Regions.US_EAST_1
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonDynamoConfig {

//    @Bean
//    fun amazonDynamoDB(): AmazonDynamoDB {
//        return AmazonDynamoDBClientBuilder
//            .standard()
//            .withEndpointConfiguration(
//                AwsClientBuilder.EndpointConfiguration("http://localhost:4566", US_EAST_1.name)
//            ).build()
//    }

    @Bean
    fun amazonDynamoDBAWS(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.SA_EAST_1)
            .withCredentials(
                AWSStaticCredentialsProvider(BasicAWSCredentials(HS_TRACKER_ACCESS_KEY, HS_TRACKER_SECRET_KEY)))
            .build()
    }

    @Bean
    fun dynamoMapper(): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDBAWS())
    }

    companion object {
        const val HS_TRACKER_ACCESS_KEY = "AKIAZTSKHMEZMYF2FMSB"
        const val HS_TRACKER_SECRET_KEY = "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"
    }
}