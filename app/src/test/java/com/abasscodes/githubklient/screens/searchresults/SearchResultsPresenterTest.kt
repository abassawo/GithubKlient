package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RepoModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.verify

class SearchResultsPresenterTest : BasePresenterTest<SearchResultsPresenter>() {
    @Mock
    lateinit var mockView: SearchResultsContract.View

    val mockModels: List<RepoModel> = initModels(100, 200, 300, 400, 500)

    private fun initModels(vararg stars: Int): List<RepoModel> {
        val models = mutableListOf<RepoModel>()
        for(star in stars){
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
    fun `test error in api triggers view to show error`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.error(Throwable()))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView, never()).showResultsFragment(mockModels.takeLast(3).asReversed())
        verify(mockView).showError()
    }

    @Test
    fun `test empty response without api failure  triggers view to show error`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(emptyList()))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView, never()).showResultsFragment(mockModels.takeLast(3).asReversed())
        verify(mockView).showError()
    }

    @Test
    fun `test correct results are shwon when api call is successful1`() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(mockModels))
        presenter.onQueryEntered("nytimes")
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView).showResultsFragment(mockModels.takeLast(3).asReversed())
    }

    @Test
    fun `test on click navigates correctly`() {
        presenter.onSearchResultClicked(mockModels[0])
        verify(mockView).navigateToDetail(mockModels[0])
    }
}