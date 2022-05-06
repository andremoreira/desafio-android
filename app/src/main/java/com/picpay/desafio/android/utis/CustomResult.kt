package com.picpay.desafio.android.utis

sealed class CustomResult<out T> {
    class CustomResultSuccess<out T>(val data:T): CustomResult<T>()
    class CustomResultError(val error: String): CustomResult<Nothing>()
}