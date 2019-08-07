package com.abasscodes.githubklient.screens.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.screens.searchresults.SearchResultsActivity
import com.abasscodes.githubklient.views.FragmentInteractionListener
import com.abasscodes.githubklient.views.adapters.tabs.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    FragmentInteractionListener {

    private lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabAdapter = TabAdapter(this, supportFragmentManager)
        GitKlientApp.instance.appComponent?.inject(this)
        setupViewPage(viewPager)
    }

    private fun setupViewPage(viewPager: ViewPager) =
        with(viewPager) {
            adapter = tabAdapter
            currentItem = 0
            tabs.setupWithViewPager(this)
        }

    override fun onCompanyClicked(company: String) {
        startActivity(SearchResultsActivity.makeIntent(this, company))
    }
}