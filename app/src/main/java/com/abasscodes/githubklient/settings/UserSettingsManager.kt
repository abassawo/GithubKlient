package com.abasscodes.githubklient.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class UserSettingsManager(context: Context) : UserSettings {
    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}