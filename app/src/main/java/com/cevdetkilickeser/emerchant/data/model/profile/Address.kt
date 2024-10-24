package com.cevdetkilickeser.emerchant.data.model.profile


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates? = Coordinates(0.0, 0.0),
    @SerializedName("country")
    val country: String,
    @SerializedName("postalCode")
    val postalCode: String? = "00000",
    @SerializedName("state")
    val state: String,
    @SerializedName("stateCode")
    val stateCode: String? = "--"
)