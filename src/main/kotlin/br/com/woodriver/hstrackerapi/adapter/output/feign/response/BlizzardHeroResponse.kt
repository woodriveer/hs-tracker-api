package br.com.woodriver.hstrackerapi.adapter.output.feign.response

import org.apache.logging.log4j.util.Strings.EMPTY

data class BlizzardHeroResponse(
    var id: Int = 0,
    var collection: Int = 0,
    var slug: String = EMPTY,
    var classId: Int = 0,
    var multiClassIds: ArrayList<String> = arrayListOf(),
    var cardTypeId: Int = 0,
    var cardSetId: Int = 0,
    var rarityId: Int? = null,
    var artistName: String? = null,
    var health: Int = 0,
    var manaCost: Int = 0,
    var name: String = EMPTY,
    var text: String = EMPTY,
    var image: String = EMPTY,
    var imageGold: String = EMPTY,
    var flavorText: String = EMPTY,
    var cropImage: String = EMPTY,
    var childIds: ArrayList<Int> = arrayListOf(),
    var battlegrounds: Battlegrounds = Battlegrounds()
) {
    data class Battlegrounds(
        var hero: Boolean = false,
        var image: String = EMPTY,
        var imageGold: String = EMPTY
    )
}