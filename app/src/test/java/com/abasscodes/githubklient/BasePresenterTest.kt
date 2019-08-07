package com.abasscodes.githubklient

import com.abasscodes.githubklient.base.BasePresenter
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.rest.RestApi
import com.abasscodes.githubklient.settings.UserSettings
import com.abasscodes.githubklient.testutil.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class BasePresenterTest<P : BasePresenter<*>> {
    lateinit var appRepository: AppRepository
    lateinit var presenter: P
    @Mock
    lateinit var mockRestApi: RestApi
    @Mock
    lateinit var mockSettings: UserSettings
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    open fun setup() {
        testSchedulerProvider = TestSchedulerProvider()
        appRepository = AppRepository(testSchedulerProvider, mockRestApi)
    }

    @Test
    fun testAppRepository() {
        Mockito.`when`(mockRestApi.searchRepo("nytimes")).thenReturn(Single.just(listOf()))
        val testObserver = appRepository.getOrderedRepos("nytimes").test()
        testObserver.assertNoErrors()
    }

}