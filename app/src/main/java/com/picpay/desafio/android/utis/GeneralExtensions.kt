package com.picpay.desafio.android.utis

import kotlin.reflect.KFunction1

inline fun<reified T : Any> CustomResult<T>.andThen(
    errorCallback: KFunction1<String, Unit>,
    successCallback: (T) -> Unit
){
    when(this){
        is CustomResult.CustomResultSuccess -> successCallback(this.data)
        is CustomResult.CustomResultError -> errorCallback(this.error)
    }
}