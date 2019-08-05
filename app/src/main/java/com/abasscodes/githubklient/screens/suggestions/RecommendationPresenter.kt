package com.abasscodes.githubklient.screens.suggestions

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class RecommendationPresenter @Inject constructor(
    settings: UserSettings,
    appRepository: AppRepository
) :
    BasePresenter<RecommendationContract.View>(settings, appRepository),
    RecommendationContract.Presenter {
    override fun onQueryEntered(text: String) {
       view?.navigatToSearchResultsFor(text)
    }

    override fun onViewBound() {
        super.onViewBound()
        view?.showRecommendations(suggestedCompanies)
    }


    companion object {
        val suggestedCompanies = RecommendedCompany.values()
    }

}