package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.base.BaseContract
import com.abasscodes.githubklient.models.RepoModel

interface SearchResultsContract {

    interface View : BaseContract.View {
        fun showResultsFragment(repoModels: List<RepoModel>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onQueryEntered(query: String)
    }
}