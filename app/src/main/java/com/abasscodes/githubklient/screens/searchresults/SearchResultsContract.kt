package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.base.BaseContract
import com.abasscodes.githubklient.models.RepoModel

interface SearchResultsContract {

    interface View : BaseContract.View {
        fun showResultsFragment(repoModels: List<RepoModel>)
        fun navigateToDetail(model: RepoModel)
        fun showEmptyContentState(visible: Boolean, query: String)
        fun showNoConnectionWarning()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onQueryEntered(query: String)
        fun onSearchResultClicked(model: RepoModel)
    }
}