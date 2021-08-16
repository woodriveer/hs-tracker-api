package br.com.woodriver.hstrackerapi.adapter.output.feign.client

import br.com.woodriver.hstrackerapi.adapter.output.feign.configuration.BlizzardFeignConfiguration
import br.com.woodriver.hstrackerapi.adapter.output.feign.request.BlizzardTokenRequest.GRANT_TYPE_CLIENT_CREDENTIALS
import br.com.woodriver.hstrackerapi.adapter.output.feign.request.BlizzardTokenRequest.GRANT_TYPE_KEY
import br.com.woodriver.hstrackerapi.adapter.output.feign.response.BlizzardTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "blizzard-oauth", url = "https://us.battle.net", configuration = [BlizzardFeignConfiguration::class])
interface BlizzardOAuthClient {
    @PostMapping(value = ["/oauth/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    fun authenticate(
        @RequestParam(value = GRANT_TYPE_KEY) grantType: String = GRANT_TYPE_CLIENT_CREDENTIALS
    ): BlizzardTokenResponse
}