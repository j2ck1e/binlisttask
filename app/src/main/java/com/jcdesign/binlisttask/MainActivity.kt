package com.jcdesign.binlisttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jcdesign.binlisttask.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.checkBtn.setOnClickListener {
            if (binding.cardNumberEditText.text.length < 8) {
                Toast.makeText(this, "Значение менее 8 символов", Toast.LENGTH_LONG).show()

            } else {
                cardNumber = binding.cardNumberEditText.text.toString()
                val retService = RetrofitInstance
                    .getRetrofitInstance()
                    .create(CardService::class.java)

                GlobalScope.launch(Dispatchers.IO) {
                    val response = retService.getCards(cardNumber)

                    withContext(Dispatchers.Main) {

                        if (response.isSuccessful) {
                            Log.i("MYTAG", "${response}")
                            binding.schemeTextview.text = "Scheme: " + response.body()?.scheme
                            binding.brandTextview.text = "Brand: " + response.body()?.brand
                            binding.lengthTextview.text = "Number length: " + response.body()?.number?.length
                            binding.typeTextview.text = "Type: " + response.body()?.type
                            binding.prepaidTextview.text = "Prepaid: " + response.body()?.prepaid
                            binding.countryTextview.text = "Country: " + response.body()?.country?.name
                            binding.nameTextview.text = "Bank: " + response.body()?.bank?.name
                            binding.siteTextview.text = response.body()?.bank?.url
                            binding.telTextview.text = "Tel.: " + response.body()?.bank?.phone

                        }else if(response.code() == 404) {
                            Toast.makeText(this@MainActivity,
                                "Не корректный номер карты",
                                Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this@MainActivity,
                                "Server isn't available",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }
}