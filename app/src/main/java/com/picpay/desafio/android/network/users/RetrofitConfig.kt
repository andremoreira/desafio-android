package com.picpay.desafio.android.network.users

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.application.App
import com.picpay.desafio.android.utis.GlobalConstants.Companion.BASE_API
import com.picpay.desafio.android.utis.Utils
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitConfig {
    companion object{
        val client: Retrofit
            get() {
              val gson: Gson =  GsonBuilder().create()
                val cacheSize  = (5 * 1024 * 1024).toLong()
                val myCache  = Cache(App.context.cacheDir, cacheSize)

                val okHttp: OkHttpClient =
                    OkHttpClient.Builder()
                        .cache(myCache)
                        .addInterceptor { chain ->
                            var request = chain.request()
                            request = if(Utils.isInternetAvailable(App.context)
                            )
                                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                            else
                                request
                                    .newBuilder()
                                    .header("Cache-Control", "public, only-if-cache, max-stale= " + 60 * 60 * 24 * 7).build()

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
