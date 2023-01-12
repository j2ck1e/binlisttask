package com.jcdesign.binlisttask


import com.google.gson.annotations.SerializedName

data class NumberItem(
    @SerializedName("length")
    val length: Int,
    @SerializedName("luhn")
    val luhn: Boolean
)