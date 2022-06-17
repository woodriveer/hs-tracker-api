package br.com.woodriver.hstrackerapi.adapter.input.web.response

import org.apache.logging.log4j.util.Strings.EMPTY

class CompletedHeroesResponse(
    val heroes: List<HeroesResponse>
) {
    open class HeroesResponse(
        var name: String = EMPTY,
        var heroId: String = EMPTY,
        var portraitURL: String = EMPTY,
        var completed: Boolean = false
    )
}