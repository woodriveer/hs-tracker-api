package br.com.woodriver.hstrackerapi.adapter.output.feign.client

import br.com.woodriver.hstrackerapi.adapter.output.feign.config.BlizzardAuthConfiguration
import br.com.woodriver.hstrackerapi.adapter.output.feign.response.BlizzardAuthResponse
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "blizzard-auth", url = "\${blizzard.api.url-auth}", configuration = [BlizzardAuthConfiguration::class])
interface BlizzardAuthClient {
    @PostMapping(value = ["/oauth/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(@RequestBody formParams: Map<String, Any>): BlizzardAuthResponse
}