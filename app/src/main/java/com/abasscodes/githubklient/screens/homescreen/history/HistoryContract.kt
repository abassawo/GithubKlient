package com.abasscodes.githubklient.screens.homescreen.history

import com.abasscodes.githubklient.base.BaseContract

interface HistoryContract {
    interface View : BaseContract.View {
        fun showStoredQueries(queries: Set<String>)
        fun showEmptyContentMessage(visible: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onHistoryItemDeleted(position: Int)
    }
}