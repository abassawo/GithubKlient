package com.abasscodes.githubklient.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class UserSettingsManager(context: Context) : UserSettings {

    override fun storeLastQuery(query: String) {
        val prevValue = preferences.getString(QUERY_SET, null)
        if(prevValue == null) {
            preferences.edit().putString(QUERY_SET, query).apply()
        } else {
            preferences.edit().putString(QUERY_SET, prevValue + "," + query).apply()
        }
    }

    override fun getLastQuerySet(): List<String> {
        val queries = preferences.getString(QUERY_SET, null)
        if(queries != null) {
            return queries.split(",").toList()
        }
        return emptyList()
    }

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        val QUERY_SET = "query_set"
    }
}