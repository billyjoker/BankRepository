package com.guillermoraya.bank.ui.main

import com.guillermoraya.domain.BankRow
import java.text.SimpleDateFormat

class MainPresenter {

    companion object {
        const val INVALID_DATE_FORMAT = "INVALID_DATE_FORMAT"
        const val DATE_PARSER = "yyyy-MM-dd'T'HH:mm:ss"
        const val DATE_FORMATTER = "dd.MM.yyyy HH:mm"
    }

    // Display the most recent transaction at the top of the screen.
    // Then, display all the other transactions ordered by date.
    // I do not know if that one is redundant, transactions are ordered
    // with most recent first, and descendents are the next items  ¯\_(ツ)_/¯
    fun sortedListByDate(bankList: List<BankRow>): List<BankRow> {

        val parser =  SimpleDateFormat(DATE_PARSER)
        val formatter = SimpleDateFormat(DATE_FORMATTER)

        return bankList.filterNot { it.date == INVALID_DATE_FORMAT }.sortedByDescending {
            formatter.format(parser.parse(it.date))
        }.asReversed().distinctBy { it.id }.toList() // If there are transactions that have the same id, the most recent one should be displayed.
    }
}