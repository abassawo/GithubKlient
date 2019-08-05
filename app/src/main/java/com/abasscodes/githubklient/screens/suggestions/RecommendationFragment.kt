package com.abasscodes.githubklient.screens.suggestions

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.R
import com.abasscodes.githubklient.base.BaseMvpFragment
import com.abasscodes.githubklient.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_recommendation.*
import javax.inject.Inject

class RecommendationFragment : BaseMvpFragment<RecommendationContract.Presenter>(),
    RecommendationContract.View {

    @Inject
    lateinit var presenter: RecommendationPresenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_recommendation

    override fun getPresenter(): RecommendationContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        setupSearchView(searchView)
        GitKlientApp.instance.appComponent?.inject(this)
        presenter.bindView(this)
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnClickListener { presenter.onSearchClicked() }
        searchView.setOnQueryTextFocusChangeListener(object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, isFocused: Boolean) {
                    presenter.onSearchClicked()
            }
        })
    }

    override fun showRecommendations() {

    }

    override fun navigateToSearch() {
        val activity = activity as? ClickableSearchListener
        hideKeyboard()
        activity?.onSearchClicked()
    }

    interface ClickableSearchListener {
        fun onSearchClicked()
    }

    companion object {
        fun newInstance(): RecommendationFragment {
            val fragment = RecommendationFragment()
            return fragment
        }
    }

}