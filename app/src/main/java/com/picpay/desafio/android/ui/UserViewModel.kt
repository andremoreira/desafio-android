package com.picpay.desafio.android.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.picpay.desafio.android.data.IRepositoryUser
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.utis.BaseViewModel
import com.picpay.desafio.android.utis.andThen
import kotlinx.coroutines.async


class UserViewModel(private val _repositoryUser: IRepositoryUser) : BaseViewModel() {

    var listUser: MutableLiveData<List<User>> = MutableLiveData()
    var errorUser: MutableLiveData<String> = MutableLiveData()


     fun getUsers() {
        jobs add async {
            _repositoryUser.getUsers().andThen({
                errorUser.postValue(it.toString())
            }, {
                listUser.postValue(it)
            })
        }
    }
}