package io.github.simonvar.numer.presentation.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.github.simonvar.numer.R

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            fragment = HelpFragment.newInstance()
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
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
