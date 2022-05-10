package com.picpay.desafio.android.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import com.picpay.desafio.android.utis.BaseApis.Companion.BASE_API
import com.picpay.desafio.android.utis.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitConfig {

    companion object {
        private var instance: Context? = null
        fun setContext(context: Context) {
            instance = context
        }

        val client: Retrofit
            get() {
                val gson: Gson = GsonBuilder().create()
                val cacheSize = (5 * 1024 * 1024).toLong()
                val myCache = instance?.let { Cache(it.cacheDir, cacheSize) }

                val okHttp: OkHttpClient =
                    OkHttpClient.Builder()
                        .cache(myCache)
                        .addInterceptor { chain ->
                            var request = chain.request()
                            request = if (Utils.isInternetAvailable(instance))
                                request.newBuilder().header("Cache-Control", "public, max-age=" + 5)
                                    .build()
                            else
                                request
                                    .newBuilder()
                                    .header(
                                        "Cache-Control",
                                        "public, only-if-cache, max-stale= " + 60 * 60 * 24 * 7
                                    ).build()
                            chain.proceed(request)
                        }
                        .build()

                return Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

    }
}
