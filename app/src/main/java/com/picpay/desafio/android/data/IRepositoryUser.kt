package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.utis.CustomResult

interface IRepositoryUser {
    suspend fun getUsers(): CustomResult<List<User>>
}