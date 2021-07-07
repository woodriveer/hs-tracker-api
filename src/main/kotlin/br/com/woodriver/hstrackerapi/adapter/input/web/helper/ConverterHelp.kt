package br.com.woodriver.hstrackerapi.adapter.input.web.helper

import br.com.woodriver.hstrackerapi.adapter.input.web.request.HeroRequest
import br.com.woodriver.hstrackerapi.adapter.input.web.response.CompletedHeroesResponse
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import com.auth0.jwt.JWT

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

private fun getUserIdFromToken(token: String): String{
    val jwt = JWT.decode(token)
    return jwt.getClaim(CLAIM_USER_ID).asString()
}

const val CLAIM_USER_ID = "userId"