package com.guillermoraya.bank.ui.main

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guillermoraya.domain.BankRow
import com.guillermoraya.bank.R
import com.guillermoraya.bank.databinding.ViewBankRowBinding.bind
import com.guillermoraya.bank.ui.common.basicDiffUtil
import com.guillermoraya.bank.ui.common.inflate

class BankRowAdapter(private val listener: (BankRow) -> Unit) :
    RecyclerView.Adapter<BankRowAdapter.ViewHolder>() {

    var bankTransactionList: List<BankRow> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_bank_row, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = bankTransactionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bankRow = bankTransactionList[position]
        holder.bind(bankRow)
        holder.itemView.setOnClickListener { listener(bankRow) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = bind(view)
        fun bind(bankRow: BankRow) = with(binding) {
            id.text = bankRow.id.toString()
            date.text = bankRow.date.toString()
            // Display a green badge on income and a red badge on expenses.
            if (bankRow.balancePositive) {
                amount.setBackgroundColor(Color.parseColor("#FF339933"))
            } else {
                amount.setBackgroundColor(Color.RED)
            }
            amount.text = bankRow.amount.toString()
            fee.text = bankRow.fee.toString()
            description.text = bankRow.description.toString()
        }
    }
}