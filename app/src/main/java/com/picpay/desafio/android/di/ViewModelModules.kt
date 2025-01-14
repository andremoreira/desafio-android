package com.picpay.desafio.android.di

import com.picpay.desafio.android.ui.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}