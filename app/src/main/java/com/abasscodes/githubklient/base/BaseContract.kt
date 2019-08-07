package com.abasscodes.githubklient.base

interface BaseContract {

    interface View {
        fun showLoadingIndicator(isLoading: Boolean)
    }

    interface Presenter<V : View> {
        fun bindView(view: V)
        fun unbindView()
        fun onViewDestroyed()
    }
}