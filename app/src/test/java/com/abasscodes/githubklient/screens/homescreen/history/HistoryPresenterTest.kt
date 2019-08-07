package com.abasscodes.githubklient.screens.homescreen.history

import com.abasscodes.githubklient.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class HistoryPresenterTest : BasePresenterTest<HistoryPresenter>() {
    @Mock lateinit var mockView: HistoryContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = HistoryPresenter(mockSettings, appRepository)
    }

    @Test
    fun `ensure presence of valid persisted search terms triggers view to show search history `() {
        whenever(mockSettings.lastQuerySet()).thenReturn(listOf("nytimes", "spotify"))
        presenter.bindView(mockView)
        verify(mockView).showStoredQueries(setOf("nytimes", "spotify"))
    }

    @Test
    fun `ensure empty content triggers view to show empty content text `() {
        whenever(mockSettings.lastQuerySet()).thenReturn(emptyList())
        presenter.bindView(mockView)
        verify(mockView, never()).showStoredQueries(ArgumentMatchers.anySet())
        verify(mockView).showEmptyContentMessage(true)
        verify(mockView).showEmptyContentMessage(true)
    }

    @Test
    fun `swipe to delete triggers view to show latest`() {
        whenever(mockSettings.lastQuerySet()).thenReturn(listOf("Test1", "Test2"))
        presenter.bindView(mockView)
        presenter.onHistoryItemDeleted(0)
        verify(mockSettings).updateSearchHistory(listOf("Test2"))
        verify(mockView).showStoredQueries(setOf("Test2"))
    }
}