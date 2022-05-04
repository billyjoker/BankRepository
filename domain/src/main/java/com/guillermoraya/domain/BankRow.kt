package com.guillermoraya.domain

data class BankRow(
    val id: Int?,
    val date: String?,
    val amount: Double?,
    val fee: Double?,
    val description: String?,
    val balancePositive: Boolean = false
)