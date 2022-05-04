package com.guillermoraya.bank.data.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankRowDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("date") var date: String,
    @SerializedName("amount") var amount: Double,
    @SerializedName("fee") val fee: Double,
    @SerializedName("description") val description: String,
    var balancePositive: Boolean = false
) : Parcelable