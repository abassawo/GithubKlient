package com.abasscodes.githubklient.screens.main

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class MainPresenter @Inject constructor(settings: UserSettings, appRepository: AppRepository) :
    BasePresenter<MainContract.View>(settings, appRepository),
    MainContract.Presenter {

    override fun onViewBound() {
        super.onViewBound()
        view?.let {
            val isNetworkAvailable = it.isNetworkAvailable()
            onConnectivityChecked(isNetworkAvailable)
        }
    }

    override fun onConnectivityChecked(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            view?.showContent(0)
        } else {
            view?.showNoInternetWarning()
        }
    }
}