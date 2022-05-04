package com.guillermoraya.bank.data.server

import retrofit2.http.GET

interface TheBankDbService {
    @GET("transactions.json")
    suspend fun listBankTransactionsAsync(): ArrayList<BankRowDTO>
}