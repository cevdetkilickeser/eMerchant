package com.cevdetkilickeser.emerchant.data.entity.product


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Review(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String,
    @SerializedName("reviewerName")
    val reviewerName: String
) : Serializable