package space.simonvar.numer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.simonvar.numer.legacy.CalculatorFragment
import space.simonvar.numer.legacy.ConverterFragment
import space.simonvar.numer.legacy.InfoFragment

class NumerActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_converter -> {
                changeFragment(ConverterFragment(), getString(R.string.menu_item_converter))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_calculator -> {
                changeFragment(CalculatorFragment(), getString(R.string.menu_title_calculator))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_info -> {
                changeFragment(InfoFragment(), getString(R.string.menu_title_info))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<BottomNavigationView>(R.id.navigation)
            .setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changeFragment(ConverterFragment(), getString(R.string.menu_title_converter))
    }

    fun changeFragment(f: Fragment, title: String, cleanStack: Boolean = true) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.activity_base_content, f)
        ft.addToBackStack(null)
        ft.commit()

        supportActionBar?.title = title
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager;
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
