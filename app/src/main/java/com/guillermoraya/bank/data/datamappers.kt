package com.guillermoraya.bank.data

import com.guillermoraya.domain.BankRow
import com.guillermoraya.bank.data.server.BankRowDTO as ServerBank

fun ServerBank.toDomainBank(): BankRow =
    BankRow(
        id,
        date,
        amount,
        fee,
        description,
        balancePositive
    )