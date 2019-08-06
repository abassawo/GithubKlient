package com.abasscodes.githubklient.screens.history

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class HistoryPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<HistoryContract.View>(settings, appRepository), HistoryContract.Presenter {

    private var data: MutableList<String>? = null

    override fun onViewBound() {
        super.onViewBound()
        val storedQueries = userSettings.getLastQuerySet()
        storedQueries.ifEmpty {
//            view?.showError()  //show empty content state
            return
        }
        storedQueries.let {
            this.data = it.toMutableList()
            view?.showStoredQueries(it.toSet())
        }
    }

    override fun onHistoryItemDeleted(position: Int) {
        data?.let {
            it.removeAt(position)
            view?.showStoredQueries(it.toSet())
            userSettings.updateSearchHistory(it)
        }
    }
}