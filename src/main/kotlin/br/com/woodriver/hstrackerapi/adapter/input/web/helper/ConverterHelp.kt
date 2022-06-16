package br.com.woodriver.hstrackerapi.adapter.input.web.helper

import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroesResponse
import br.com.woodriver.hstrackerapi.adapter.input.web.response.CompletedHeroesResponse
import br.com.woodriver.hstrackerapi.adapter.output.feign.response.BlizzardHeroResponse
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import com.auth0.jwt.JWT

fun String.toDomain() = User(id = getUserIdFromToken(this), hashMapOf())

fun User.toResponse(): CompletedHeroesResponse {
    val heroList = arrayListOf<CompletedHeroesResponse.HeroesResponse>()
    val heroes = heroes.values
    for (hero in heroes) {
        heroList.add(
            CompletedHeroesResponse.HeroesResponse(
                name = hero.name, 
                portraitURL = hero.portraitURL,
                heroId = hero.heroId,
                completed = hero.completed
            )
        )
    }
    return CompletedHeroesResponse(heroList)
}

fun HeroRequest.toDomain(token: String) = User(
    id = getUserIdFromToken(token),
    heroes = hashMapOf(Pair(heroName, Hero(heroName, true)))
    )

fun String.toHeroDomain(): Hero =
    Hero(name = this, completed = false, heroId = this)

private fun getUserIdFromToken(token: String): String{
    val jwt = JWT.decode(token)
    return jwt.getClaim(CLAIM_USER_ID).asString()
}

fun Hero.toResponse(): HeroResponse =
    HeroResponse(
        heroId = heroId,
        heroName = name,
        portraitURL = portraitURL
    )

fun List<Hero>.toResponse(): HeroesResponse =
    HeroesResponse(
        heroes = map {
            it.toResponse()
        }
    )

fun BlizzardHeroResponse.toDomain(): Hero =
    Hero(
        name = name,
        completed = false,
        heroId = slug,
        portraitURL = battlegrounds.imageGold
    )

const val CLAIM_USER_ID = "user_name"