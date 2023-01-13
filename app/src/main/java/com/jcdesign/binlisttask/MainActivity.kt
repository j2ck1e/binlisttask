package com.jcdesign.binlisttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var checkButton: Button
    private lateinit var scheme: TextView
    private lateinit var type: TextView
    private lateinit var cardNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.cardNumber_editText)
        checkButton = findViewById(R.id.check_btn)
        scheme = findViewById(R.id.scheme_textview)
        type = findViewById(R.id.type_textview)




        checkButton.setOnClickListener {
            cardNumber = inputNumber.text.toString()


            val retService = RetrofitInstance
                .getRetrofitInstance()
                .create(CardService::class.java)



            GlobalScope.launch(Dispatchers.IO) {
                val response = retService.getCards(cardNumber)
                Log.i("MYTAG", "${response}")
                scheme.text = response.scheme
                type.text = response.type
            }
        }

    }
}