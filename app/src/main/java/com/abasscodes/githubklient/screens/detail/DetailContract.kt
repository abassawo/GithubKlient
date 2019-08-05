package com.abasscodes.githubklient.screens.detail

import com.abasscodes.githubklient.base.BaseContract

interface DetailContract {
    interface View : BaseContract.View {
        fun showWebView(url: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onUrlDispatched(dispatchedUrl: String)
    }
}