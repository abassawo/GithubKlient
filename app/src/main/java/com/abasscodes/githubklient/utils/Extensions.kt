package com.abasscodes.githubklient.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import com.abasscodes.githubklient.screens.suggestions.SearchAndSuggestionFragment
import com.abasscodes.githubklient.views.adapters.history.HistoryAdapter
import com.abasscodes.githubklient.views.adapters.recommendations.RecommendationsAdapter
import com.abasscodes.githubklient.views.adapters.searchresults.SearchResultsAdapter

fun RecommendationsAdapter.inflateView(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): View =
    LayoutInflater.from(viewGroup.context).inflate(layoutRes, viewGroup, false)

fun SearchResultsAdapter.inflateView(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): View =
    LayoutInflater.from(viewGroup.context).inflate(layoutRes, viewGroup, false)

fun HistoryAdapter.inflateView(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): View =
    LayoutInflater.from(viewGroup.context).inflate(layoutRes, viewGroup, false)

fun FragmentActivity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view!!.windowToken, 0)
}


fun SearchAndSuggestionFragment.hideKeyboard() {
    activity?.hideKeyboard()
}