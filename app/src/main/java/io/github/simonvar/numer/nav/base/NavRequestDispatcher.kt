package io.github.simonvar.numer.nav.base

abstract class NavRequestDispatcher(private val firstNode: Any) {

    fun dispatch(request: NavRequest) {

        var node: Any? = firstNode

        do {
            if (node is NavRequestHandler && node.handleNavRequest(request)) {
                return
            }
            node = getParent(node)
        } while (node != null)

        throw NotHandledNavRequestException()
    }

    abstract fun getParent(node: Any?): Any?
}