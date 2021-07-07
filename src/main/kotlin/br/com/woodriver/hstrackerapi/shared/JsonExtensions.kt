package br.com.woodriver.hstrackerapi.shared

import br.com.woodriver.hstrackerapi.application.domain.Hero
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import org.apache.logging.log4j.util.Strings

fun Any.objectToJson(): String = Gson().toJson(this)


inline fun <reified T> String.jsonToObject() : T {
    return Gson().fromJson(this, T::class.java)
}

fun LinkedTreeMap<String, LinkedTreeMap<String, Any>>.toHeroHashMap(): HashMap<String, Hero> {
    val hash = hashMapOf<String, Hero>()
    forEach {
        var completed = false
        var heroName = Strings.EMPTY
        it.value.forEach { other ->
            when(other.key) {
                "completed" -> completed = other.value as Boolean
                else -> heroName = other.value as String
            }
        }
        hash[it.key] = Hero(heroName, completed)
    }
    return hash
}