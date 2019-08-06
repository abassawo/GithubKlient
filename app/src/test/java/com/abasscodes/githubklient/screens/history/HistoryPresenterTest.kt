package com.abasscodes.githubklient.screens.history

import androidx.lifecycle.ViewModel
import com.abasscodes.githubklient.BasePresenterTest
import com.abasscodes.githubklient.models.RepoModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.`when` as whenever
import org.mockito.Mockito.verify

class HistoryPresenterTest : BasePresenterTest<HistoryPresenter>() {
    @Mock lateinit var mockView: HistoryContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = HistoryPresenter(mockSettings, appRepository)
    }

    @Test
    fun `ensure presence of valid persisted search terms triggers view to show search history `() {
        whenever(mockSettings.getLastQuerySet()).thenReturn(listOf("nytimes", "spotify"))
        presenter.bindView(mockView)
        verify(mockView).showStoredQueries(setOf("nytimes", "spotify"))
    }

    @Test
    fun `ensure empty search terms list triggers view to show no history`() {
        val emptyList = emptyList<String>()
        whenever(mockSettings.getLastQuerySet()).thenReturn(emptyList)
        presenter.bindView(mockView)
        verify(mockView, never()).showStoredQueries(emptyList.toSet())
    }
}