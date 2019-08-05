package com.abasscodes.githubklient.base

interface BaseContract {

    interface View {
        fun showError()
    }

    interface Presenter<V : View> {
        fun bindView(view: V)
        fun unbindView()
        fun onViewDestroyed()
    }
}