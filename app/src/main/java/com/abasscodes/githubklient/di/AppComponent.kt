package com.abasscodes.githubklient.di

import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.screens.detail.DetailActivity
import com.abasscodes.githubklient.screens.homescreen.history.HistoryFragment
import com.abasscodes.githubklient.screens.homescreen.MainActivity
import com.abasscodes.githubklient.screens.searchresults.SearchResultsActivity
import com.abasscodes.githubklient.screens.homescreen.suggestions.SearchAndSuggestionFragment
import com.abasscodes.githubklient.utils.connectivity.ConnectivityInterceptor
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(app: GitKlientApp)
    fun inject(searchResultsActivity: SearchResultsActivity)
    fun inject(detailActivity: DetailActivity)
    fun inject(historyFragment: HistoryFragment)
    fun inject(searchAndSuggestionFragment: SearchAndSuggestionFragment)
    fun inject(connectivityInterceptor: ConnectivityInterceptor)
}