package com.abasscodes.githubklient.screens.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.viewpager.widget.ViewPager
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpActivity
import com.abasscodes.githubklient.models.PageNames
import com.abasscodes.githubklient.screens.searchresults.SearchResultsActivity
import com.abasscodes.githubklient.screens.suggestions.RecommendationFragment
import com.abasscodes.githubklient.models.RecommendedCompany
import com.abasscodes.githubklient.utils.connectivity.ConnectivityUtil
import com.abasscodes.githubklient.views.adapters.tabs.TabAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View, RecommendationFragment.FragmentInteractionListener {
    @Inject
    lateinit var presenter: MainPresenter

    override fun getPresenter(): MainContract.Presenter = presenter

    override fun getLayoutResource() = R.layout.activity_main

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        GitKlientApp.instance.appComponent?.inject(this)
        setupViewPage(viewPager)
        presenter.bindView(this)
    }

    private fun setupViewPage(viewPager: ViewPager) =
        with(viewPager) {
            adapter = TabAdapter(
                this@MainActivity,
                supportFragmentManager
            )
            currentItem = 0
            tabs.setupWithViewPager(this)
        }


    override fun showNoInternetWarning() {
        Snackbar.make(viewPager, getString(R.string.internet_down_msg), Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.check_internet_settings) {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()
    }

    override fun onCompanyClicked(companyName: String) {
        startActivity(SearchResultsActivity.makeIntent(this, companyName))
    }


    override fun showContent(index: Int) {
        viewPager.currentItem = PageNames.Search.ordinal
    }

    override fun isNetworkAvailable(): Boolean {
        return ConnectivityUtil.isNetworkAvailable(this)
    }

}
