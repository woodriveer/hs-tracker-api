package br.com.woodriver.hstrackerapi.adapter.output.feign.config

import feign.auth.BasicAuthRequestInterceptor
import feign.codec.Encoder
import feign.form.FormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean

class BlizzardAuthConfiguration(
    private val blizzardProperties: BlizzardProperties,
    private val messageConverters: ObjectFactory<HttpMessageConverters>
) {
    @Bean
    fun basicAuthRequestInterceptor(): BasicAuthRequestInterceptor {
        return BasicAuthRequestInterceptor(blizzardProperties.clientId, blizzardProperties.clientSecret)
    }

    @Bean
    fun feignFormEncoded(): Encoder = FormEncoder(SpringEncoder(messageConverters))

    companion object {
        const val GRANT_TYPE = "grant_type"
        const val CLIENT_CREDENTIALS = "client_credentials"
    }
}