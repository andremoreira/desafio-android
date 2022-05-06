package com.picpay.desafio.android.utis

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.listen(lifecycleOwner: LifecycleOwner, onData: (T) -> Unit){
    return this.observe(lifecycleOwner, Observer { data -> data?.let{ onData(data) }})
}