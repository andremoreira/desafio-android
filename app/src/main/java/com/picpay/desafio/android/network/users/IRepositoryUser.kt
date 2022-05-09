package com.picpay.desafio.android.network.users

import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.utis.CustomResult

interface IRepositoryUser {
    suspend fun getUsers(): CustomResult<List<User>>
}