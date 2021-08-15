package br.com.woodriver.hstrackerapi.adapter.input.web.helper

import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.CompletedHeroesResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.response.HeroesResponse
import br.com.woodriver.hstrackerapi.application.domain.BlizzardHero
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import com.auth0.jwt.JWT
import org.apache.logging.log4j.util.Strings
import org.apache.logging.log4j.util.Strings.EMPTY

fun String.toDomain() = User(id = getUserIdFromToken(this), hashMapOf())

fun User.toResponse(): CompletedHeroesResponse {
    val heroList = arrayListOf<CompletedHeroesResponse.HeroesResponse>()
    val heroes = heroes.values
    for (hero in heroes) {
        heroList.add(CompletedHeroesResponse.HeroesResponse(hero.name, hero.completed))
    }
    return CompletedHeroesResponse(heroList)
}

fun HeroRequest.toDomain(token: String) = User(
    id = getUserIdFromToken(token),
    heroes = hashMapOf(Pair(heroName, Hero(heroName, true)))
    )

fun HeroRequest.toDomainBlizzardHero(): BlizzardHero =
        BlizzardHero(
                heroId = heroName,
                name = EMPTY,
                imageUrl = EMPTY
        )

fun List<BlizzardHero>.toResponse(): List<HeroesResponse> =
        map {
            it.toResponse()
        }

fun BlizzardHero.toResponse(): HeroesResponse =
        HeroesResponse(
                name = name,
                imageUrl = imageUrl,
                heroId = heroId
        )

private fun getUserIdFromToken(token: String): String{
    val jwt = JWT.decode(token)
    return jwt.getClaim(CLAIM_USER_ID).asString()
}

const val CLAIM_USER_ID = "userId"