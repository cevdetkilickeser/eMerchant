package com.cevdetkilickeser.emerchant.data.entity.profile


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("stateCode")
    val stateCode: String
)