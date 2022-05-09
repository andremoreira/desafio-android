package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.users.IRepositoryUser
import com.picpay.desafio.android.network.users.RepositoryUser
import org.koin.dsl.module

val repositoryModule = module {
    single<IRepositoryUser> { RepositoryUser() }
}