package br.com.woodriver.hstrackerapi.adapter.output.feign.config

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.auth.BasicAuthRequestInterceptor
import feign.codec.Encoder
import feign.form.FormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder
import springfox.documentation.spi.service.contexts.SecurityContextBuilder

class BlizzardAPIConfiguration(
    private val blizzardProperties: BlizzardProperties,
    private val messageConverters: ObjectFactory<HttpMessageConverters>
): RequestInterceptor {
    @Bean
    fun basicAuthRequestInterceptor(): BasicAuthRequestInterceptor {
        return BasicAuthRequestInterceptor(blizzardProperties.clientId, blizzardProperties.clientSecret)
    }


    @Bean
    fun feignFormEncoded(): Encoder = FormEncoder(SpringEncoder(messageConverters))

    companion object {
        const val GRANT_TYPE = "grant_type"
        const val CLIENT_CREDENTIALS = "client_credentials"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun apply(template: RequestTemplate) {
        val authentication = SecurityContextHolder.getContext().authentication

        template.header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, ))
    }
}