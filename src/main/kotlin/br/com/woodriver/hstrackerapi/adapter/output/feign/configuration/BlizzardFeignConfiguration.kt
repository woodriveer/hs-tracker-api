package br.com.woodriver.hstrackerapi.adapter.output.feign.configuration

import br.com.woodriver.hstrackerapi.adapter.output.feign.BlizzardProperties
import feign.auth.BasicAuthRequestInterceptor
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean

class BlizzardFeignConfiguration(private val blizzardProperties: BlizzardProperties) {

    @Bean
    fun feignFormEncoder(converters: ObjectFactory<HttpMessageConverters>): Encoder =
        SpringFormEncoder(SpringEncoder(converters))

    @Bean
    fun basicAuthInterceptor(): BasicAuthRequestInterceptor =
        BasicAuthRequestInterceptor(blizzardProperties.key, blizzardProperties.secret)
}