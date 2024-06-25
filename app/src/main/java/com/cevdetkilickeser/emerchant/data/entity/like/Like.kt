package com.cevdetkilickeser.emerchant.data.entity.like

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likes")
data class Like(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "productId") val productId: String
)
