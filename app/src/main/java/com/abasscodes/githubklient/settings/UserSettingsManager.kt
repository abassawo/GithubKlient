package com.abasscodes.githubklient.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class UserSettingsManager(context: Context) : UserSettings {
    override fun storeLastQuery(query: String) {
        preferences.edit().putString(QUERY_SET, query).apply()
    }

    override fun getLastQuerySet(): String = preferences.getString(QUERY_SET, null)

//    override fun storeQuery(query: String) {
//        val set = getQuerySet() ?: mutableSetOf()
//        set.add(query)
//        preferences.edit().putStringSet(QUERY_SET, set).apply()
//    }
//
//    override fun getQuerySet(): MutableSet<String>? = preferences.getStringSet(QUERY_SET, null)

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        val QUERY_SET = "query_set"
    }
}