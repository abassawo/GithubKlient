package com.abasscodes.githubklient.settings

interface UserSettings {
    fun storeLastQuery(query: String)
    fun getLastQuerySet() : List<String>
    fun updateSearchHistory(historyItems: List<String>)
}