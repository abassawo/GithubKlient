package com.abasscodes.githubklient.screens.homescreen.suggestions

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.models.RecommendedCompany
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class SearchAndSuggestionPresenter @Inject constructor(settings: UserSettings, appRepository: AppRepository) :
    BasePresenter<SearchAndSuggestionContract.View>(settings, appRepository),
    SearchAndSuggestionContract.Presenter {

    override fun onQueryEntered(text: String) {
       view?.navigatToSearchResultsFor(text)
    }

    override fun onViewBound() {
        super.onViewBound()
        view?.showRecommendations(RecommendedCompany.values())
    }
}