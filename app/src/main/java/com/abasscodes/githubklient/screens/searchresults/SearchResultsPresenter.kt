package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.base.BaseScreenState
import com.abasscodes.githubklient.base.BaseScreenState.Loading
import com.abasscodes.githubklient.base.ScreenState
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.utils.connectivity.NoConnectivityException
import com.abasscodes.githubklient.settings.UserSettings
import timber.log.Timber
import javax.inject.Inject

class SearchResultsPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<SearchResultsContract.View>(settings, appRepository),
    SearchResultsContract.Presenter {

    private var query: String? = null

    override fun onQueryEntered(query: String) {
        userSettings.storeLastQuery(query)
        this.query = query
        onScreenStateUpdated(Loading)
        val disposable = appRepository.getOrderedRepos(query)
            .subscribe(
                { response ->
                    onScreenStateUpdated(SearchResultsScreenState.SearchResultsLoaded(response, query)
                    )
                },
                { e -> onScreenStateUpdated(BaseScreenState.Error(e)) })

        disposables.add(disposable)
    }

    override fun onSearchResultClicked(model: RepoModel) {
        view?.navigateToDetail(model)
    }

    private fun onScreenStateUpdated(screenState: ScreenState) {
        view?.showLoadingIndicator(screenState is Loading)

        when (screenState) {
            is SearchResultsScreenState.EmptyContentState -> {
                with(screenState) {
                    view?.showEmptyContentState(this.showEmptyContent, this.query)
                }
            }
            is SearchResultsScreenState.SearchResultsLoaded -> {
                if (screenState.content.isNullOrEmpty()) {
                    query?.let { onScreenStateUpdated(SearchResultsScreenState.EmptyContentState(true, it)) }
                } else {
                    view?.showResultsFragment(screenState.content)
                }
            }
            is BaseScreenState.Error -> {
                Timber.e(screenState.errorMsg)
                query?.let { onScreenStateUpdated(SearchResultsScreenState.EmptyContentState(true, it)) }
                if(screenState.errorMsg is NoConnectivityException) {
                    view?.showNoConnectionWarning()
                }
            }
        }
    }

    companion object {
        const val NUM_TOP_RATED_ITEMS = 3
    }
}