package com.guillermoraya.data.repository

import com.guillermoraya.data.source.RemoteDataSource
import com.guillermoraya.domain.BankRow

class BankRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getBankTransactions(): List<BankRow> {
        return remoteDataSource.getBankTransactions()
    }

}