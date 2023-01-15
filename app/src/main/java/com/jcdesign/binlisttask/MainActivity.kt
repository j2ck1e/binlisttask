package com.jcdesign.binlisttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.jcdesign.binlisttask.databinding.ActivityMainBinding
import com.jcdesign.binlisttask.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cardNumber: String

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter

    private lateinit var db: AppDatabase
    private lateinit var todoLiveData: LiveData<List<RecyclerItem>>
    private lateinit var data: List<RecyclerItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        recyclerView = binding.mainRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter(mutableListOf())
        recyclerView.adapter = adapter


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name")
            .allowMainThreadQueries()
            .build()

        todoLiveData = db.CardDao().getAllItems()

        todoLiveData.observe(this, Observer {
            data = it
            adapter.updateList(it)
            Log.d("MyLog", "-> $it")
            screenDataValidation(it)
        })



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

                            addItem(RecyclerItem(0,
                                "Scheme: " + response.body()?.scheme,
                                "Brand: " + response.body()?.brand,
                                "Number length: " + response.body()?.number?.length,
                                "Type: " + response.body()?.type,
                                "Prepaid: " + response.body()?.prepaid,
                                "Country: " + response.body()?.country?.name,
                                "Bank: " + response.body()?.bank?.name,
                                response.body()?.bank?.url ?: "url isn't found",
                                "Tel.: " + response.body()?.bank?.phone))


                        } else if (response.code() == 404) {
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

    private fun screenDataValidation(list: List<RecyclerItem>) {
        if (list.isEmpty()) {

            recyclerView.visibility = INVISIBLE
        } else {

            recyclerView.visibility = VISIBLE
        }
    }

    fun addItem(item: RecyclerItem) {
        recyclerView.visibility = VISIBLE
        db.CardDao().insertItem(item)
    }
}