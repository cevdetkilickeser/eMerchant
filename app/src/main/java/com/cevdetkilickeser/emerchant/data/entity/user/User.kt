package com.cevdetkilickeser.emerchant.data.entity.user


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
) : Serializable