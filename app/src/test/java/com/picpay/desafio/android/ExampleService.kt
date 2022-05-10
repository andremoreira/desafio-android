package com.picpay.desafio.android

import com.picpay.desafio.android.network.users.api.PicPayService
import com.picpay.desafio.android.network.users.models.User

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}