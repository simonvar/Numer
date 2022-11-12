package space.simonvar.numer;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class HelpActivity extends SingleFragmentActivity {

    public static final String EXTRA_NUMBER = "com.shine.semav.numbersystem.number";
    public static final String EXTRA_SYSTEM_FROM = "com.shine.semav.numbersystem.system_from";
    public static final String EXTRA_SYSTEM_TO = "com.shine.semav.numbersystem.system_to";


    public static Intent newIntent(Context packageContext, String number, String systemFrom, String systemTo){
        Intent i = new Intent(packageContext, HelpActivity.class);
        i.putExtra(EXTRA_NUMBER, number);
        i.putExtra(EXTRA_SYSTEM_FROM, systemFrom);
        i.putExtra(EXTRA_SYSTEM_TO, systemTo);
        return i;
    }


    @Override
    protected Fragment createFragment() {
        return HelpFragment.newInstance();
    }
}
