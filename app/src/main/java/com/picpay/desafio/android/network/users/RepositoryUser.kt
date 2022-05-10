package com.picpay.desafio.android.network.users

import com.picpay.desafio.android.network.users.api.PicPayService
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.utis.CustomResult

class RepositoryUser : IRepositoryUser {

    private val service = RetrofitConfig.client.create(PicPayService::class.java)

    override suspend fun getUsers(): CustomResult<List<User>> {
        return try {
            val result: List<User> = service.getUsers()
            if(!result.isNullOrEmpty()){
                CustomResult.CustomResultSuccess(result)
            }else{
                CustomResult.CustomResultError("Houve um erro! Tente novamente")
            }
             } catch (e: Exception) {
            CustomResult.CustomResultError(e.toString())
        }

    }
}
