package br.com.woodriver.hstrackerapi.adapter.output.feign

import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardClient
import br.com.woodriver.hstrackerapi.adapter.output.feign.client.BlizzardOAuthClient
import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero
import br.com.woodriver.hstrackerapi.application.port.output.HeroesInfoPort
import br.com.woodriver.hstrackerapi.exceptions.HeroException
import feign.FeignException
import org.springframework.stereotype.Component

@Component
class BlizzardFeignClient(
    val blizzardOAuthClient: BlizzardOAuthClient,
    val blizzardClient: BlizzardClient
): HeroesInfoPort {
    override fun getHeroOtherInfo(heroId: String): BlizzardHero {
        val token = blizzardOAuthClient.authenticate()
        val blizzardHero: BlizzardHero
        try {
            val informationResult = blizzardClient.getHeroInformation(
                heroId = heroId,
                authorization = "Bearer ${token.accessToken}"
            )
            blizzardHero = BlizzardHero(
                heroId = informationResult.slug,
                name = informationResult.name,
                imageURL = informationResult.battlegrounds.imageGold
            )
            if (!informationResult.battlegrounds.hero)
                throw HeroException("HR001", heroId, "Could not save non hero card.")
        } catch (ex: FeignException.NotFound) {
            throw HeroException("HR003", heroId, "Could not find $heroId at blizzard database")
        } catch (ex: HeroException) {
            throw ex
        } catch (ex: Exception) {
            throw HeroException("HR002", heroId, "Could not make valid request to blizzard")
        }

        return blizzardHero
    }
}