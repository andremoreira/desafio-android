package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.IRepositoryUser
import com.picpay.desafio.android.data.RepositoryUser
import org.koin.dsl.module

val repositoryModule = module {
    single<IRepositoryUser> { RepositoryUser() }
}