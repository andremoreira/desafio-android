package com.picpay.desafio.android.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.utis.GlobalConstants.Companion.BASE_API
import com.picpay.desafio.android.utis.Utils
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitConfig {


    fun getPicPayService(): PicPayService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(PicPayService::class.java)
    }
}