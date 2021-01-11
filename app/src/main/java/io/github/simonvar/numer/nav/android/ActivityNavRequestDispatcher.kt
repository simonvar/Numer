package io.github.simonvar.numer.nav.android

import androidx.appcompat.app.AppCompatActivity
import io.github.simonvar.numer.nav.base.NavRequestDispatcher

class ActivityNavRequestDispatcher(
    activity: AppCompatActivity
) : NavRequestDispatcher(activity) {

    override fun getParent(node: Any?): Any? = null
}