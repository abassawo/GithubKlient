package com.abasscodes.githubklient.screens.history

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class HistoryPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<HistoryContract.View>(settings, appRepository), HistoryContract.Presenter {

    override fun onViewBound() {
        super.onViewBound()
        val storedQueries = userSettings.getLastQuerySet()
        storedQueries?.let { view?.showStoredQueries(it.toSet()) }
    }
}