package br.com.woodriver.hstrackerapi.exceptions

class HeroException(val errorCode: String, val heroId: String, override val message: String): RuntimeException()