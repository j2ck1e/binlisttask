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


data class BankItem(
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("url")
    val url: String
)


data class CountryItem(
    @SerializedName("alpha2")
    val alpha2: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("emoji")
    val emoji: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("numeric")
    val numeric: String
)


data class NumberItem(
    @SerializedName("length")
    val length: Int,
    @SerializedName("luhn")
    val luhn: Boolean
)