package com.abasscodes.githubklient.base

interface BaseContract {

    interface View

    interface Presenter<V : View> {
        fun bindView(view: V)
        fun unbindView()
        fun onViewDestroyed()
    }
}