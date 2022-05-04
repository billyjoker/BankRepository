package com.guillermoraya.bank.di

import com.guillermoraya.data.source.RemoteDataSource
import com.guillermoraya.bank.data.server.TheBankDbDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = TheBankDbDataSource()
}