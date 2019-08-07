package com.abasscodes.githubklient.screens.searchresults

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpActivity
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.screens.detail.DetailActivity
import com.abasscodes.githubklient.views.adapters.searchresults.SearchResultsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_search_results.*
import javax.inject.Inject

class SearchResultsActivity : BaseMvpActivity<SearchResultsContract.Presenter>(),
    SearchResultsContract.View, SearchResultsAdapter.OnSearchResultClickListener {

    @Inject
    lateinit var presenter: SearchResultsPresenter
    private val adapter: SearchResultsAdapter = SearchResultsAdapter(this)

    override fun getPresenter(): SearchResultsContract.Presenter = presenter

    override fun getLayoutResource(): Int = R.layout.activity_search_results

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        GitKlientApp.instance.appComponent?.inject(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerView(recyclerView)
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
        presenter.onQueryEntered(intent.extras[QUERY_KEY].toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onSearchResultClick(model: RepoModel) {
        presenter.onSearchResultClicked(model)
    }

    override fun showResultsFragment(repoModels: List<RepoModel>) = adapter.setData(repoModels)

    override fun showEmptyContentState(visible: Boolean, query: String) {
        noResultTextView.text = getString(R.string.no_search_results, query)
        noResultTextView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showLoadingIndicator(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showNoConnectionWarning() =
        Snackbar.make(recyclerView, R.string.no_connection_available, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.check_internet_settings) {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()


    override fun navigateToDetail(model: RepoModel) =
        startActivity(DetailActivity.makeIntent(this, model))

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    companion object {

        private const val QUERY_KEY: String = "arg_query_key"

        fun makeIntent(context: Context, query: String): Intent {
            return Intent(context, SearchResultsActivity::class.java).putExtra(QUERY_KEY, query)
        }
    }

}