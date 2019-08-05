package com.abasscodes.githubklient.di

import android.content.Context
import com.abasscodes.githubklient.BuildConfig
import com.abasscodes.githubklient.GitKlientApp
import com.abasscodes.githubklient.rest.AppRepository
import com.abasscodes.githubklient.rest.RestApi
import com.abasscodes.githubklient.settings.UserSettings
import com.abasscodes.githubklient.settings.UserSettingsManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: GitKlientApp) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)


    @Provides
    @Singleton
    fun provideUserSettingsManager(): UserSettings = UserSettingsManager(application)

    @Provides
    @Singleton
    fun provideRepository(
        restApi: RestApi,
        schedulerProvider: SchedulerProvider
    ): AppRepository {
        return AppRepository(schedulerProvider, restApi)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}