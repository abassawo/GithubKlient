package com.abasscodes.githubklient.screens.main

import com.abasscodes.githubklient.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when` as whenever

class MainPresenterTest : BasePresenterTest<MainPresenter>() {
    @Mock lateinit var mockView: MainContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = MainPresenter(mockSettings, appRepository)
    }

    @Test
    fun `onViewBound should load Content if Network is Available`() {
        whenever(mockView.isNetworkAvailable()).thenReturn(true)
        presenter.bindView(mockView)
        Mockito.verify(mockView, Mockito.never()).showNoInternetWarning()
        Mockito.verify(mockView).showContent(ArgumentMatchers.anyInt())
    }

    @Test
    fun `onViewBound should load Error is Network is Not Available`() {
        whenever(mockView.isNetworkAvailable()).thenReturn(false)
        presenter.bindView(mockView)
        Mockito.verify(mockView).showNoInternetWarning()
        Mockito.verify(mockView, Mockito.never()).showContent(ArgumentMatchers.anyInt())
    }

}