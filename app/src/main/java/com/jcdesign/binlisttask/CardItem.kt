package com.jcdesign.binlisttask


import com.google.gson.annotations.SerializedName

data class CardItem(
    @SerializedName("bank")
    val bank: BankItem,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("country")
    val country: CountryItem,
    @SerializedName("number")
    val number: NumberItem,
    @SerializedName("prepaid")
    val prepaid: Boolean,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String
)