package br.com.woodriver.hstrackerapi.utils

import br.com.woodriver.hstrackerapi.application.domain.Hero
import br.com.woodriver.hstrackerapi.application.domain.User
import org.jeasy.random.EasyRandom
import org.jeasy.random.randomizers.EmailRandomizer

inline fun <reified T> randomObject(): T {
    val easyRandom = EasyRandom()
    return easyRandom.nextObject(T::class.java)
}

fun randomUserWithEmptyPortrait(heroesCount: Int): User {
    val randomUser: User = randomObject()
    val heroes = hashMapOf<String, Hero>()
    for (count in 1..heroesCount) {
        val hero: Hero = randomObject<Hero>().copy(
            heroId = EmailRandomizer().randomValue
        )
        heroes[hero.heroId] = hero.copy(portraitURL = "")
    }

    return randomUser.copy(
        heroes = heroes
    )
}