package com.cevdetkilickeser.emerchant.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cevdetkilickeser.emerchant.data.order.Order

interface OrderDao {

    @Query("SELECT * FROM orders WHERE userId = :userId")
    suspend fun getOrdersFromRoom(userId: String): List<Order>

    @Query("SELECT * FROM orders WHERE userId = :userId AND productId = :productId")
    suspend fun checkOrderOnRoom(userId: String, productId: String): Order

    @Insert
    suspend fun addOrderToRoom(order: Order)

    @Delete
    suspend fun deleteOrderFroRoom(order: Order)

    @Update
    suspend fun updateOrderOnRoom(orderId: Int, userId: String, productId: String, quantity: String)
}