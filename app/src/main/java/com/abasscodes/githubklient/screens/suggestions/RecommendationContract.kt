package com.abasscodes.githubklient.screens.suggestions

import com.abasscodes.githubklient.base.BaseContract

interface RecommendationContract {

    interface View : BaseContract.View {
        fun navigatToSearchResultsFor(companyName: String)
        fun showRecommendations(suggestedCompanies: Array<RecommendedCompany>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onQueryEntered(text: String)
    }
}