package com.guillermoraya.usecases

import com.guillermoraya.data.repository.BankRepository
import com.guillermoraya.domain.BankRow

class GetBankTransactionInfo(private val bankRepository: BankRepository) {
    suspend fun invoke(): List<BankRow> = bankRepository.getBankTransactions()
}