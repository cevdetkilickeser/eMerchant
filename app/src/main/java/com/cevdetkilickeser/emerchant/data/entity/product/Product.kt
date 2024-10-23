package com.cevdetkilickeser.emerchant.data.entity.product


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("availabilityStatus")
    val availabilityStatus: String,
    @SerializedName("brand")
    val brand: String? = "Unknown",
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("dimensions")
    val dimensions: Dimensions,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("returnPolicy")
    val returnPolicy: String,
    @SerializedName("reviews")
    val reviews: List<Review>,
    @SerializedName("shippingInformation")
    val shippingInformation: String,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("stock")
    val stock: Int,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("warrantyInformation")
    val warrantyInformation: String,
    @SerializedName("weight")
    val weight: Int
) : Parcelable