package com.picpay.desafio.android.utis

inline fun<reified T : Any> CustomResult<T>.andThen(
    errorCallback: (Any) -> Unit,
    successCallback: (T) -> Unit
){
    when(this){
        is CustomResult.CustomResultSuccess -> successCallback(this.data)
        is CustomResult.CustomResultError -> errorCallback(this.error)
    }
}