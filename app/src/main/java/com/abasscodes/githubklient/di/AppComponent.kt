package com.abasscodes.githubklient.di

import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.screens.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(app: GitKlientApp)
}