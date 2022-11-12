package com.shine.semav.numbersystem.common;

import android.content.Context;
import android.preference.PreferenceManager;

public class StringPreferences {
    private static final String PREF_STRING_NUMBER = "numberPref";
    private static final String PREF_STRING_FROM = "fromPref";
    private static final String PREF_STRING_TO = "toPref";

    public static String getNumber(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_STRING_NUMBER, null);
    }

    public static String getSystemFrom(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_STRING_FROM, null);
    }

    public static String getSystemTo(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_STRING_TO, null);
    }

    public static void saveStrings(Context context, String number, String systemFrom, String systemTo){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_STRING_NUMBER, number)
                .putString(PREF_STRING_FROM, systemFrom)
                .putString(PREF_STRING_TO, systemTo)
                .apply();
    }


}
