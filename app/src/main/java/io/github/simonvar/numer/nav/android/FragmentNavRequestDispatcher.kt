package io.github.simonvar.numer.nav.android

import androidx.fragment.app.Fragment
import io.github.simonvar.numer.nav.base.NavRequestDispatcher

class FragmentNavRequestDispatcher(
    fragment: Fragment
) : NavRequestDispatcher(fragment) {

    override fun getParent(node: Any?): Any? {
        return if (node is Fragment) {
            node.parentFragment ?: node.activity
        } else {
            null
        }
    }
}
