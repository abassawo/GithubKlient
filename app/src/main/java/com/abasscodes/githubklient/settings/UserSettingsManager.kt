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
            preferences.edit().putString(QUERY_SET, "$prevValue,$query").apply()
        }
    }

    override fun updateSearchHistory(historyItems: List<String>) {
        val searchHistoryText = StringBuilder()
        for((index, _) in historyItems.withIndex()) {
            if(index == historyItems.size - 1) {
                searchHistoryText.append(historyItems[index])
            } else {
                searchHistoryText.append(historyItems[index] + ",")
            }
        }
        preferences.edit().putString(QUERY_SET, searchHistoryText.toString()).apply()
    }

    override fun lastQuerySet(): List<String> {
        val queries = preferences.getString(QUERY_SET, null)
        if(queries != null) {
            val history = queries.split(",").toMutableSet()
            if(history.contains("")){
                history.remove("")
            }
            return history.toList()
        }
        return emptyList()
    }

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        const val QUERY_SET = "query_set"
    }
}