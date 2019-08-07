package com.abasscodes.githubklient.utils.connectivity

import android.content.Context
import com.abasscodes.githubklient.GitKlientApp
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ConnectivityInterceptor : Interceptor {
    @Inject lateinit var context: Context

    init {
        GitKlientApp.instance.appComponent?.inject(this)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (ConnectivityUtil.isNetworkAvailable(context)) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectivityException()
        }
    }
}

open class NoConnectivityException : Throwable() {
    override val message: String? = "Internet connection not available"
}