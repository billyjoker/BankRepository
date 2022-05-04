package com.guillermoraya.bank.di

import com.guillermoraya.data.repository.BankRepository
import com.guillermoraya.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun bankRepositoryProvider(
        remoteDataSource: RemoteDataSource
    ) = BankRepository(remoteDataSource)
}