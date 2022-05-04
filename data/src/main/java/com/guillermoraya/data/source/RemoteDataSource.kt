package com.guillermoraya.data.source

import com.guillermoraya.domain.BankRow

interface RemoteDataSource {
    suspend fun getBankTransactions(): ArrayList<BankRow>
}