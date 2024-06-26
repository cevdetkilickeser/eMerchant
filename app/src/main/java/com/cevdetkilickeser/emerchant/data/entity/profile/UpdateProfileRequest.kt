package com.cevdetkilickeser.emerchant.data.entity.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(

    @SerializedName("age")
    val age: Int,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("bloodGroup")
    val bloodGroup: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair")
    val hair: Hair,
    @SerializedName("height")
    val height: Double,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("weight")
    val weight: Double
) {
    val color: String
        get() = hair.color
    val type: String
        get() = hair.type
}
