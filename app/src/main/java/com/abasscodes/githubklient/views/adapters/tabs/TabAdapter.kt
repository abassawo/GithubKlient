package com.abasscodes.githubklient.views.adapters.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.models.PageNames
import com.abasscodes.githubklient.screens.homescreen.history.HistoryFragment
import com.abasscodes.githubklient.screens.homescreen.suggestions.SearchAndSuggestionFragment

class TabAdapter(context: Context, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()

    private val historyFragment: HistoryFragment = HistoryFragment()

    init {
        for (value in PageNames.values()) {
            when (value) {
                PageNames.Search ->  addFragment(SearchAndSuggestionFragment.newInstance(), context.getString(R.string.search))
                PageNames.HistoryPage -> addFragment(historyFragment, context.getString(R.string.history))
            }
        }
        notifyDataSetChanged()
    }

    private fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

    fun refresh(position: Int) {
        when(position){
            PageNames.HistoryPage.ordinal -> historyFragment.refresh()
        }
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}