package br.com.woodriver.hstrackerapi.exceptions

import java.lang.RuntimeException

class UnauthorizedException(override val message: String): RuntimeException(message)