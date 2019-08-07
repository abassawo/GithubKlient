package com.abasscodes.githubklient.screens.homescreen.history

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.base.BaseScreenState
import com.abasscodes.githubklient.base.ScreenState
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.screens.homescreen.history.HistoryScreenState.HistoryContentLoaded
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class HistoryPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<HistoryContract.View>(settings, appRepository), HistoryContract.Presenter {

    private var data: MutableList<String>? = null

    override fun onViewBound() {
        super.onViewBound()
        onScreenStateUpdated(BaseScreenState.Loading)
        onScreenStateUpdated(HistoryContentLoaded(userSettings.lastQuerySet().toSet()))
    }

    private fun onScreenStateUpdated(screenState: ScreenState) {
        view?.showLoadingIndicator(screenState is BaseScreenState.Loading)
        view?.showEmptyContentMessage(screenState is HistoryScreenState.EmptyContentState && screenState.showEmptyContent)

        when (screenState) {
            is HistoryContentLoaded -> {
                if (screenState.content.isNullOrEmpty()) {
                    onScreenStateUpdated(HistoryScreenState.EmptyContentState(true))
                } else {
                    this.data = screenState.content.toMutableList()
                    view?.showStoredQueries(screenState.content)
                }
            }
        }
    }

    override fun onHistoryItemDeleted(position: Int) {
        data?.let {
            it.removeAt(position)
            userSettings.updateSearchHistory(it)
            onScreenStateUpdated(HistoryScreenState.HistoryContentLoaded(it.toSet()))
        }
    }
}