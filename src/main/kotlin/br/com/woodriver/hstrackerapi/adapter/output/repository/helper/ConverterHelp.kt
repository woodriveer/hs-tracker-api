package br.com.woodriver.hstrackerapi.adapter.output.repository.helper

import br.com.woodriver.hstrackerapi.adapter.output.repository.HeroEntity
import br.com.woodriver.hstrackerapi.adapter.output.repository.UserEntity
import br.com.woodriver.hstrackerapi.adapter.output.repository.UserEntity.Companion.USER_INFO
import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import br.com.woodriver.hstrackerapi.shared.jsonToObject
import br.com.woodriver.hstrackerapi.shared.objectToJson
import br.com.woodriver.hstrackerapi.shared.toHeroHashMap
import com.google.gson.internal.LinkedTreeMap


fun User.toEntity(): UserEntity =
    UserEntity(
        userId = id,
        type = USER_INFO,
        heroes = heroes.objectToJson()
    )

fun UserEntity.toDomain(): User {
    val mapHeroes: LinkedTreeMap<String, LinkedTreeMap<String, Any>> = heroes.jsonToObject()
    return User(
        id = userId,
        heroes = mapHeroes.toHeroHashMap()
    )
}

fun Hero.toEntity(): HeroEntity =
    HeroEntity(
        heroId = heroId,
        heroName = name,
        portraitURL = portraitURL
    )

fun HeroEntity.toDomain(): Hero {
    return Hero(
        name = heroName,
        completed = false,
        portraitURL = portraitURL,
        heroId = heroId
    )
}