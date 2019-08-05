package com.abasscodes.githubklient.di

import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.screens.main.MainActivity
import com.abasscodes.githubklient.screens.searchresults.SearchResultsActivity
import com.abasscodes.githubklient.screens.suggestions.RecommendationFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(app: GitKlientApp)
    fun inject(recommendationFragment: RecommendationFragment)
    fun inject(searchResultsActivity: SearchResultsActivity)
}