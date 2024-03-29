package com.abasscodes.githubklient.rest

import com.abasscodes.githubklient.di.SchedulerProvider
import com.abasscodes.githubklient.models.RepoModel
import com.abasscodes.githubklient.screens.searchresults.SearchResultsPresenter
import com.abasscodes.githubklient.utils.rx.SchedulerTransformer
import io.reactivex.Single
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val restApi: RestApi
) {

    private fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> {
        return SchedulerTransformer(
            schedulerProvider.ioScheduler(),
            schedulerProvider.uiScheduler()
        )
    }

    fun getOrderedRepos(companyName: String): Single<List<RepoModel>> = searchRepo(companyName).map {
        it.sortedByDescending { model -> model.stargazers_count }.take(
            SearchResultsPresenter.NUM_TOP_RATED_ITEMS
        )
    }

    private fun searchRepo(companyName: String): Single<List<RepoModel>> = restApi.searchRepo(companyName)
      .compose(subscribeOnIoObserveOnUi())
}