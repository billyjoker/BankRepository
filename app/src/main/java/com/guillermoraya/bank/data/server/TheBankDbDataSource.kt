package com.guillermoraya.bank.data.server

import com.guillermoraya.bank.data.toDomainBank
import com.guillermoraya.bank.ui.main.MainPresenter.Companion.DATE_FORMATTER
import com.guillermoraya.bank.ui.main.MainPresenter.Companion.DATE_PARSER
import com.guillermoraya.bank.ui.main.MainPresenter.Companion.INVALID_DATE_FORMAT
import com.guillermoraya.data.source.RemoteDataSource
import com.guillermoraya.domain.BankRow
import java.text.SimpleDateFormat
import java.util.*

class TheBankDbDataSource : RemoteDataSource {

    override suspend fun getBankTransactions():  ArrayList<BankRow> =
        TheBankDb.service
            .listBankTransactionsAsync()
            .map {

                parseDate(it)
                parseAmountWithfeed(it)

                it.toDomainBank()
            } as ArrayList<BankRow>

    private fun parseAmountWithfeed(it: BankRowDTO) {
        // prepare for display the total amount of each transaction with the fee applied.
        // I do not know exactly the kind of operation to be performed to apply the fee to the amount
        it.amount = it.amount + it.fee
        if (it.amount > 0)  {
            it.balancePositive = true
        }
    }

    private fun parseDate(it: BankRowDTO) {
        val parser = SimpleDateFormat(DATE_PARSER)
        val formatter = SimpleDateFormat(DATE_FORMATTER)

        var error = false

        try {
            formatter.format(parser.parse(it.date))
        } catch (e: Exception) {
            error = true
        } finally {
            if (error) {
                // Do not list the transactions with an invalid date format.
                it.date = INVALID_DATE_FORMAT
            }
        }
    }
}