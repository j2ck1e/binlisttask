package com.jcdesign.binlisttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(CardService::class.java)

        val responceLiveData: LiveData<Response<Cards>> = liveData {
            val responce: Response<Cards> = retService.getCards()
            emit(responce)
        }
        responceLiveData.observe(this, Observer{
            val cardsList = it.body()?.listIterator()
            if(cardsList!=null) {
                while (cardsList.hasNext()){
                    val cardsItem = cardsList.next()
                    Log.i("MYTAG", "${cardsItem}")
                }
            }
        })
    }
}