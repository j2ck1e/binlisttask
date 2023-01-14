package com.jcdesign.binlisttask.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jcdesign.binlisttask.CardItem
import com.jcdesign.binlisttask.RecyclerItem

@Dao
interface CardDao {
    @Query("SELECT * FROM recyclerItem") // name of table
    fun getAllItems(): LiveData<List<RecyclerItem>>

    @Insert
    fun insertItem(recyclerItem: RecyclerItem)

}