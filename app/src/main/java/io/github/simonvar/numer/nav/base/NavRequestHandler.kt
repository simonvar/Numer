package io.github.simonvar.numer.nav.base

interface NavRequestHandler {

    fun handleNavRequest(message: NavRequest): Boolean
}
