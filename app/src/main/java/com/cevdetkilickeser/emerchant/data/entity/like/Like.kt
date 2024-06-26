package com.cevdetkilickeser.emerchant.data.entity.like

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cevdetkilickeser.emerchant.data.entity.product.Product

@Entity(tableName = "likes")
data class Like(
    @PrimaryKey(autoGenerate = true) val likeId: Int = 0,
    @ColumnInfo(name = "userId") val userId: String,
    @Embedded val product: Product
) {
    val productId: Int
        get() = product.id
}
