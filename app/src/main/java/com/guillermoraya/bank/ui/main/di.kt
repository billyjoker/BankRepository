package com.guillermoraya.bank.ui.main

import com.guillermoraya.data.repository.BankRepository
import com.guillermoraya.usecases.GetBankTransactionInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPopularBankTransactionProvider(bankRepository: BankRepository) =
        GetBankTransactionInfo(bankRepository)
}