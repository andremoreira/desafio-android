package com.picpay.desafio.android.data.api

import com.picpay.desafio.android.data.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    fun getUsers(): Deferred<Response<List<User>>>
}