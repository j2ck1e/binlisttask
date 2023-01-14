package com.jcdesign.binlisttask

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecyclerItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheme: String,
    val brand: String,
    val length: Int,
    val type: String,
    val prepaid: Boolean,
    val country: String,
    val name: String,
    val site: String,
    val tel: String,
)