package com.abasscodes.githubklient.screens.detail

import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RecommendedCompany
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

class DetailPresenterTest : BasePresenterTest<DetailPresenter>() {
    @Mock
    lateinit var mockView: DetailContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = DetailPresenter(mockSettings, appRepository)
        presenter.bindView(mockView)
    }

    @Test
    fun `test onUrlDispatched triggers view to show webview`() {
       presenter.onUrlDispatched(RecommendedCompany.NYTimes.githubName)
        verify(mockView).showWebView(RecommendedCompany.NYTimes.githubName)
    }
}