package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class SearchResultsPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<SearchResultsContract.View>(settings, appRepository),
    SearchResultsContract.Presenter {

    override fun onQueryEntered(query: String) {
        userSettings.storeLastQuery(query)
        val disposable = appRepository.searchRepo(query)
            .subscribe(
                { response -> showResults(response) },
                { e -> showError(e) })

        disposables.add(disposable)
    }

    private fun showResults(response: List<RepoModel>?) {
        if (response.isNullOrEmpty()) {
            view?.showError() // todo - set up sealed class
        } else view?.showResultsFragment(response)
    }

    override fun onSearchResultClicked(model: RepoModel) {
        view?.navigateToDetail(model)
    }

    private fun showError(e: Any) {
        view?.showError()
    }

    companion object {
        val NUM_TOP_RATED_ITEMS = 3
    }
}