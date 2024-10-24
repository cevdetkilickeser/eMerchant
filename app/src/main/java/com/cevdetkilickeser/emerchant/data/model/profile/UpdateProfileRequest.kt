package com.cevdetkilickeser.emerchant.data.model.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(

    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: Address
)
//
//{
//    val addressDetail: String
//        get() = address.address
//    val city: String
//        get() = address.city
//    val state: String
//        get() = address.state
//    val country: String
//        get() = address.country
//
//}
