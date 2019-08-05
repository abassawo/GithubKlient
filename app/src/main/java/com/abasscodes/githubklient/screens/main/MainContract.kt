package com.abasscodes.githubklient.screens.main

import com.abasscodes.githubklient.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun showNoInternetWarning()
        fun showContent(index: Int)
        fun isNetworkAvailable(): Boolean
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onConnectivityChecked(isNetworkAvailable: Boolean)
    }
}