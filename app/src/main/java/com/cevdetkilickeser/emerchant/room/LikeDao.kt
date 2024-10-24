package com.cevdetkilickeser.emerchant.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cevdetkilickeser.emerchant.data.model.like.Like

@Dao
interface LikeDao {

    @Query("SELECT * FROM likes WHERE userId = :userId")
    suspend fun getLikes(userId: String): List<Like>

    @Query("SELECT * FROM likes WHERE userId = :userId AND id = :productId")
    suspend fun checkLike(userId: String, productId: Int): Like

    @Insert
    suspend fun insertLike(like: Like)

    @Delete
    suspend fun deleteLike(like: Like)

}