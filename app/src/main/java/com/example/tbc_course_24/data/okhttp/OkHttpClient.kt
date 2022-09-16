package com.example.tbc_course_24.data.okhttp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request


object OkHttpClient {


    fun okHttpClient (context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(offlineInterceptor(context))
            .addNetworkInterceptor(onlineInterceptor())
            .cache(getCache(context))
            .build()


    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    private fun onlineInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    private fun offlineInterceptor(context:Context) = Interceptor { chain ->
        var request: Request = chain.request()
        if (!isNetworkAvailable(context)) {
            val maxStale = 60 * 60 * 24 * 30
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    private fun getCache(context: Context): Cache {
        val cacheSize = (20 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, cacheSize)
    }
}