package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.utils.connectivity.NoConnectivityException
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class SearchResultsPresenterTest : BasePresenterTest<SearchResultsPresenter>() {
    @Mock
    lateinit var mockView: SearchResultsContract.View

    private val mockModels: List<RepoModel> = initModels(100, 200, 300, 400, 500)

    private fun initModels(vararg stars: Int): List<RepoModel> {
        val models = mutableListOf<RepoModel>()
        for (star in stars) {
            models.add(RepoModel("test ", star, "", ""))
        }
        return models
    }

    @Before
    override fun setup() {
        super.setup()
        presenter = SearchResultsPresenter(mockSettings, appRepository)
        presenter.bindView(mockView)
    }

    @Test
    fun `test error due to network connection triggers view to show network warning`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.error(NoConnectivityException()))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView).showNoConnectionWarning()
    }

    @Test
    fun `test error in api triggers view to show error`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.error(Throwable()))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView, never()).showResultsFragment(mockModels.takeLast(3).asReversed())
        verify(mockView).showEmptyContentState(true, "nytimes")
    }

    @Test
    fun `test empty response without api failure  triggers view to show error`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(emptyList()))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView, never()).showResultsFragment(mockModels.takeLast(3).asReversed())
        verify(mockView).showEmptyContentState(true, "nytimes")
    }

    @Test
    fun `test correct results are shown when api call is successful1`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(mockModels))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView).showResultsFragment(mockModels.takeLast(3).asReversed())
    }

    @Test
    fun `ensure org with less than 3 starred repos shows results if less than 3`() {
        val mockModels = initModels(100, 200)
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(mockModels))

        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()

        val count = Math.min(SearchResultsPresenter.NUM_TOP_RATED_ITEMS, mockModels.size)
        assertEquals(count, 2)
        verify(mockView).showResultsFragment(mockModels.takeLast(count).asReversed())
    }

    @Test
    fun `test on click navigates correctly`() {
        presenter.onSearchResultClicked(mockModels[0])
        verify(mockView).navigateToDetail(mockModels[0])
    }
}