package com.abasscodes.githubklient.screens.detail

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    settings: UserSettings, appRepository: AppRepository
) : BasePresenter<DetailContract.View>(settings, appRepository), DetailContract.Presenter {

    override fun onUrlDispatched(dispatchedUrl: String) {
        view?.showWebView(dispatchedUrl)
    }
}