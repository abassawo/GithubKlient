package com.abasscodes.githubklient.screens.homescreen.history

import com.abasscodes.githubklient.base.ScreenState

sealed class HistoryScreenState : ScreenState {
    data class HistoryContentLoaded(val content: Set<String>) : HistoryScreenState()
    class EmptyContentState(val showEmptyContent: Boolean) : HistoryScreenState()
}