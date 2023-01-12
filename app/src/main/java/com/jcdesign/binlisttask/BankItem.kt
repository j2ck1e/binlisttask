package com.jcdesign.binlisttask


import com.google.gson.annotations.SerializedName

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