package io.github.simonvar.numer.presentation.help

import android.content.Context
import android.content.Intent

import androidx.fragment.app.Fragment
import io.github.simonvar.numer.base.SingleFragmentActivity

class HelpActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return HelpFragment.newInstance()
    }

    companion object {
        val EXTRA_NUMBER = "com.shine.semav.numbersystem.number"
        val EXTRA_SYSTEM_FROM = "com.shine.semav.numbersystem.system_from"
        val EXTRA_SYSTEM_TO = "com.shine.semav.numbersystem.system_to"

        fun newIntent(packageContext: Context, number: String, systemFrom: String, systemTo: String): Intent {
            val i = Intent(packageContext, HelpActivity::class.java)
            i.putExtra(EXTRA_NUMBER, number)
            i.putExtra(EXTRA_SYSTEM_FROM, systemFrom)
            i.putExtra(EXTRA_SYSTEM_TO, systemTo)
            return i
        }
    }
}
