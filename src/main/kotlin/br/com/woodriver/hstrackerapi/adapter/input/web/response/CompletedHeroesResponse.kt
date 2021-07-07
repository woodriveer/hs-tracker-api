package br.com.woodriver.hstrackerapi.adapter.input.web.response

import org.apache.logging.log4j.util.Strings

class CompletedHeroesResponse(
    val heroes: List<HeroesResponse>
) {
    open class HeroesResponse(
        var name: String = Strings.EMPTY,
        var completed: Boolean = false
    )
}