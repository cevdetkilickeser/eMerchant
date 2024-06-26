package com.cevdetkilickeser.emerchant.data.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "productId") val productId: String,
    @ColumnInfo(name = "quantity") val quantity: String,
)
