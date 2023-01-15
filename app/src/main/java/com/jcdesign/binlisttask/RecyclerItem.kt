package com.jcdesign.binlisttask

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecyclerItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheme: String,
    val brand: String,
    val length: String,
    val type: String,
    val prepaid: String,
    val country: String,
    val name: String,
    val site: String,
    val tel: String,
)