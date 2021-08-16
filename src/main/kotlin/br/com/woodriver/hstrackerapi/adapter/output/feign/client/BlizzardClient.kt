package br.com.woodriver.hstrackerapi.adapter.output.feign.client

import br.com.woodriver.hstrackerapi.adapter.input.web.helper.GameModes.BATTLEGROUNDS
import br.com.woodriver.hstrackerapi.adapter.input.web.helper.Locales.LOCALE_US
import br.com.woodriver.hstrackerapi.adapter.output.feign.response.HeroInformationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "blizzard-hs-api", url = "https://us.api.blizzard.com")
interface BlizzardClient {

    @GetMapping(value = ["/hearthstone/cards/{heroId}"])
    fun getHeroInformation(
        @PathVariable heroId: String,
        @RequestParam locale: String = LOCALE_US,
        @RequestParam gameMode: String = BATTLEGROUNDS,
        @RequestHeader(value = "Authorization") authorization: String
    ): HeroInformationResponse
}

/*
@FeignClient(name = "blizzard-oauth", url = "https://us.battle.net", configuration = [BlizzardFeignConfiguration::class])
interface BlizzardOAuthClient {
    @PostMapping(value = ["/oauth/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    fun authenticate(
        @RequestParam(value = GRANT_TYPE_KEY) grantType: String = GRANT_TYPE_CLIENT_CREDENTIALS
    ): BlizzardTokenResponse
}
 */