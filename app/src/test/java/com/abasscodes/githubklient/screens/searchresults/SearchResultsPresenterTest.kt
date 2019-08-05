package com.abasscodes.githubklient.screens.searchresults

import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RepoModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.verify

class SearchResultsPresenterTest : BasePresenterTest<SearchResultsPresenter>() {
    @Mock
    lateinit var mockView: SearchResultsContract.View

    @Mock
    lateinit var mockModels: List<RepoModel>

    @Before
    override fun setup() {
        super.setup()
        presenter = SearchResultsPresenter(mockSettings, appRepository)
        presenter.bindView(mockView)
    }


    @Test
    fun test() {
        whenever(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(mockModels))
//        whenever(appRepository.searchRepo("nytimes")).thenReturn(Single.just(mockModels))
        presenter.onQueryEntered("nytimes")
        verify(mockView).showResultsFragment(mockModels)
    }
}