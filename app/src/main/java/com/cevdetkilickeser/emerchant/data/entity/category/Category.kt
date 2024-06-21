package com.cevdetkilickeser.emerchant.data.entity.category


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("url")
    val url: String
) : Serializable