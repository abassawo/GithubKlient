package com.abasscodes.githubklient.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class UserSettingsManager(context: Context) : UserSettings {

    override fun storeQuery(query: String) {
        val set = getQuerySet()
        set.add(query)
        preferences.edit().putStringSet(QUERY_SET, set).apply()
    }

    override fun getQuerySet(): MutableSet<String> = preferences.getStringSet(QUERY_SET, setOf())

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        val QUERY_SET = "query_set"
    }
}