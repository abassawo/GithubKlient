package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.base.ScreenState
import com.abasscodes.githubklient.models.RepoModel

sealed class SearchResultsScreenState : ScreenState {
    data class SearchResultsLoaded(
        val content: List<RepoModel>,
        val query: String
    ): SearchResultsScreenState()
    class EmptyContentState(val showEmptyContent: Boolean, val query: String) : SearchResultsScreenState()
}