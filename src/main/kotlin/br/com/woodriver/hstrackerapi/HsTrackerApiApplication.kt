package br.com.woodriver.hstrackerapi

import br.com.woodriver.hstrackerapi.adapter.output.repository.config.DynamoProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(DynamoProperties::class)
@EnableFeignClients
class HsTrackerApiApplication

fun main(args: Array<String>) {
    runApplication<HsTrackerApiApplication>(*args)
}
