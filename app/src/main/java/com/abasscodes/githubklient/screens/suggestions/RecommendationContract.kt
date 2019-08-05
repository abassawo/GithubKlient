package com.abasscodes.githubklient.screens.suggestions

import com.abasscodes.githubklient.base.BaseContract
import com.abasscodes.githubklient.models.RecommendedCompany

interface RecommendationContract {

    interface View : BaseContract.View {
        fun navigatToSearchResultsFor(companyName: String)
        fun showRecommendations(suggestedCompanies: Array<RecommendedCompany>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onQueryEntered(text: String)
    }
}