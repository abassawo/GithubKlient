package com.abasscodes.githubklient.screens.suggestions

import com.abasscodes.githubklient.base.BaseContract

interface RecommendationContract {

    interface View : BaseContract.View {
        fun navigateToSearch()
        fun showRecommendations()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onSearchClicked()
    }
}