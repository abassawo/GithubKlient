package com.abasscodes.githubklient.base

import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.settings.UserSettings
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(
    val userSettings: UserSettings,
    val appRepository: AppRepository
) : BaseContract.Presenter<V> {

    val disposables: CompositeDisposable = CompositeDisposable()
    var view: V? = null
    private var isViewBound: Boolean = false

    override fun bindView(view: V) {
        this.view = view

        if (!isViewBound) {
            onViewBound()
            isViewBound = true
        }
    }

    override fun unbindView() {
        disposables.clear()
        this.view = null

        if (isViewBound) {
            onViewUnbound()
            isViewBound = true
        }
    }

    override fun onViewDestroyed() {
    }

    private fun onViewUnbound() {
    }

    open fun onViewBound() {

    }
}