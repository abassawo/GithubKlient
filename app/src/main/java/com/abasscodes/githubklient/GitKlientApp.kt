package com.abasscodes.githubklient

import android.app.Application
import com.abasscodes.githubklient.di.AppComponent
import com.abasscodes.githubklient.di.AppModule
import com.abasscodes.githubklient.di.DaggerAppComponent
import timber.log.Timber

class GitKlientApp : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
        appComponent?.inject(this)
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var instance: GitKlientApp
            private set
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
