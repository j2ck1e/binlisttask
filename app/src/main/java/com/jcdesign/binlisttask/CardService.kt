package com.jcdesign.binlisttask

import retrofit2.Response
import retrofit2.http.GET


interface CardService {
    @GET("/5536")
    suspend fun getCards() : Response<Cards>
}