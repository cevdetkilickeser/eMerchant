package com.cevdetkilickeser.emerchant.data.entity.product


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dimensions(
    @SerializedName("depth")
    val depth: Double,
    @SerializedName("height")
    val height: Double,
    @SerializedName("width")
    val width: Double
) : Serializable