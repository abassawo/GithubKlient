package com.abasscodes.githubklient.screens.suggestions

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpFragment
import com.abasscodes.githubklient.models.RecommendedCompany
import com.abasscodes.githubklient.screens.searchresults.SearchResultsActivity
import com.abasscodes.githubklient.views.AdapterClickListener
import com.abasscodes.githubklient.views.adapters.recommendations.RecommendationsAdapter
import kotlinx.android.synthetic.main.fragment_recommendation.*
import timber.log.Timber
import javax.inject.Inject

class RecommendationFragment : BaseMvpFragment<RecommendationContract.Presenter>(),
    RecommendationContract.View, AdapterClickListener {

    @Inject
    lateinit var presenter: RecommendationPresenter
    val adapter: RecommendationsAdapter =
        RecommendationsAdapter(this)

    override fun getLayoutResourceId(): Int = R.layout.fragment_recommendation

    override fun getPresenter(): RecommendationContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        setupSearchView(searchView)
        setupRecyclerView(suggestionsRecyclerView)
        GitKlientApp.instance.appComponent?.inject(this)
        presenter.bindView(this)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.onQueryEntered(query)
                searchView.onActionViewCollapsed()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Timber.d("Query text submitted: $newText")
                return false
            }
        })
    }

    override fun showRecommendations(suggestedCompanies: Array<RecommendedCompany>) {
        adapter.setData(suggestedCompanies.toList())
    }

    override fun navigatToSearchResultsFor(companyName: String) {
        activity?.let { startActivity(SearchResultsActivity.makeIntent(it, companyName)) }
    }

    override fun onCompanyClicked(recommendedCompany: String) {
        val activity = activity as? FragmentInteractionListener
        activity?.onCompanyClicked(recommendedCompany)
    }


    interface FragmentInteractionListener {
        fun onCompanyClicked(company: String)
    }

    companion object {
        fun newInstance(): RecommendationFragment {
            val fragment = RecommendationFragment()
            return fragment
        }
    }

}