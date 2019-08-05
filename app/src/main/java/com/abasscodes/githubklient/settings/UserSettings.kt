package com.abasscodes.githubklient.settings

interface UserSettings {
//    fun storeQuery(query: String)
//    fun getQuerySet() : Set<String>?

    fun storeLastQuery(query: String)
    fun getLastQuerySet() : List<String>
}