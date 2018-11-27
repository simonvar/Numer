package com.shine.semav.numbersystem.common

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by semav on 18.09.2016.
 */
object StringPreferences {
    private val PREF_STRING_NUMBER = "numberPref"
    private val PREF_STRING_FROM = "fromPref"
    private val PREF_STRING_TO = "toPref"

    fun getNumber(context: Context): String? = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_STRING_NUMBER, "")


    fun getSystemFrom(context: Context): String? = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_STRING_FROM, "")


    fun getSystemTo(context: Context): String? = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_STRING_TO, "")


    fun saveStrings(context: Context, number: String, systemFrom: String, systemTo: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_STRING_NUMBER, number)
                .putString(PREF_STRING_FROM, systemFrom)
                .putString(PREF_STRING_TO, systemTo)
                .apply()
    }


}
