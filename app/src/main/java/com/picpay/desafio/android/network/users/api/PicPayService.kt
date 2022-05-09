package com.picpay.desafio.android.network.users.api

import com.picpay.desafio.android.network.users.models.User
import retrofit2.http.GET

interface PicPayService {
    @GET("users")
    suspend fun getUsers():List<User>
}