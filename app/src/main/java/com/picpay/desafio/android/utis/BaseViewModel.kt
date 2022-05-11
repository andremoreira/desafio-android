package com.picpay.desafio.android.utis

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel(), LifecycleObserver, CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    protected val jobs = ArrayList<Job>()

    infix fun ArrayList<Job>.add(job: Job){
        this.add(job)
    }
}