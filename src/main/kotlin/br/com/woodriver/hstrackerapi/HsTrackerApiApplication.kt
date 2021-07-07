package br.com.woodriver.hstrackerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class HsTrackerApiApplication

fun main(args: Array<String>) {
    runApplication<HsTrackerApiApplication>(*args)
}
