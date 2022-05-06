package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.utis.CustomResult

class RepositoryUser : IRepositoryUser {
    private val service = RetrofitConfig().getPicPayService()

    override suspend fun getUsers(): CustomResult<List<User>> {
        return try {
            val request = service.getUsers()
            val response = request.await()
            val data: List<User>? = response.body()

            return if (!data.isNullOrEmpty()) {
                CustomResult.CustomResultSuccess(data)
            } else {
                CustomResult.CustomResultError("Lista Vazia")
            }
        } catch (e: Exception) {
            CustomResult.CustomResultError(e.toString())
        }

    }
}
