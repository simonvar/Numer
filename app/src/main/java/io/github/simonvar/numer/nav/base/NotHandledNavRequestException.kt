package io.github.simonvar.numer.nav.base

class NotHandledNavRequestException
    : RuntimeException("You have no NavRequestHandler to handle the request. Forgot to add?")
