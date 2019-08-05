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
        userSettings.storeQuery(query)
        val disposable = appRepository.searchRepo(query)
            .subscribe(
                { response -> showResults(response) },
                { e -> showError(e) })

        disposables.add(disposable)
    }

    private fun showResults(response: List<RepoModel>?) {
        response?.let {
            val subList = it.sortedByDescending { it.stargazers_count }.take(NUM_TOP_RATED_ITEMS)
            view?.showResultsFragment(subList)
        }
    }

    override fun onSearchResultClicked(model: RepoModel) {
        view?.navigateToDetail(model)
    }


    private fun showError(e: Any) {
        view?.showError()
    }


    override fun onViewBound() {
        super.onViewBound()
    }

    companion object {
        val NUM_TOP_RATED_ITEMS = 3
    }

}