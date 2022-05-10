package com.picpay.desafio.android.ui

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.network.users.IRepositoryUser
import com.picpay.desafio.android.network.users.models.User
import com.picpay.desafio.android.utis.BaseViewModel
import com.picpay.desafio.android.utis.andThen
import kotlinx.coroutines.async


class UserViewModel(private val _repositoryUser: IRepositoryUser) : BaseViewModel() {

    var _listUser: MutableLiveData<List<User>> = MutableLiveData()
    var _errorUser: MutableLiveData<String> = MutableLiveData()

    fun getUsers() {
        jobs add async {
            _repositoryUser.getUsers().andThen(_errorUser::postValue) {
                _listUser.postValue(it)
            }
        }
    }

}