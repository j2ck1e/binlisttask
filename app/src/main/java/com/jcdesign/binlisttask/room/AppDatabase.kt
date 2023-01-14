package com.jcdesign.binlisttask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jcdesign.binlisttask.RecyclerItem

@Database(entities = [RecyclerItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun CardDao(): CardDao
}