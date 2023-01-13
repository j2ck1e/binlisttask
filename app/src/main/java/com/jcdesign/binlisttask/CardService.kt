package com.jcdesign.binlisttask

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CardService {

    @GET("{cardNumber}")
    suspend fun getCards(@Path("cardNumber") cardNumber: String) : Response<CardItem>
}