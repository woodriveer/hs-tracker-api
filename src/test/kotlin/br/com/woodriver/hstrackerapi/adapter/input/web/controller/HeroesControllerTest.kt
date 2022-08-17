package br.com.woodriver.hstrackerapi.adapter.input.web.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
internal class HeroesControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

//    @Test
//    fun getHeroInfo() {
//        val heroName = "60214-dinotamer-brann"
//        mockMvc.perform(get("/heroes/${heroName}"))
//    }

    @Test
    fun getAllHeroes() {
    }
}