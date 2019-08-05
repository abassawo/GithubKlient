package com.abasscodes.githubklient.screens.history

import com.abasscodes.githubklient.base.BaseContract

interface HistoryContract {
    interface View : BaseContract.View {
        fun showStoredQueries(queries: Set<String>)
    }

    interface Presenter : BaseContract.Presenter<View> {

    }
}