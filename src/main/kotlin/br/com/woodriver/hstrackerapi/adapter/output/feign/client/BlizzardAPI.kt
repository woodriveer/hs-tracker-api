package br.com.woodriver.hstrackerapi.adapter.output.feign.client

import br.com.woodriver.hstrackerapi.adapter.output.feign.response.BlizzardHeroResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "blizzard-api", url = "\${blizzard.api.url}")
interface BlizzardAPI {
    @GetMapping(value = ["/hearthstone/cards/{hero}?locale=en_US&gameMode=battlegrounds"])
    fun getHeroInformation(
        @PathVariable hero: String,
        @RequestHeader(name = "Authorization") authorization: String
    ): BlizzardHeroResponse
}