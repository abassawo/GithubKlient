package com.abasscodes.githubklient.screens.homescreen.suggestions

import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RecommendedCompany
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

class SearchAndSuggestionPresenterTest : BasePresenterTest<SearchAndSuggestionPresenter>() {
    @Mock
    lateinit var mockView: SearchAndSuggestionContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = SearchAndSuggestionPresenter(mockSettings, appRepository)
    }

    @Test
    fun `ensure mockView shows suggestions`() {
        presenter.bindView(mockView)
        verify(mockView).showRecommendations(RecommendedCompany.values())
    }

    @Test
    fun `ensure entrered search triggers view to navigate to results page`() {
        presenter.bindView(mockView)
        presenter.onQueryEntered(RecommendedCompany.NYTimes.githubName)
        verify(mockView).navigatToSearchResultsFor(RecommendedCompany.NYTimes.githubName)
    }
}