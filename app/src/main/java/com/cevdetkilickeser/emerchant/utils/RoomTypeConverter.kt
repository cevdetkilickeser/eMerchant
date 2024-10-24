package com.cevdetkilickeser.emerchant.utils

import androidx.room.TypeConverter
import com.cevdetkilickeser.emerchant.data.model.product.Dimensions
import com.cevdetkilickeser.emerchant.data.model.product.Meta
import com.cevdetkilickeser.emerchant.data.model.product.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {
    @TypeConverter
    fun fromDimensions(dimensions: Dimensions?): String? {
        return Gson().toJson(dimensions)
    }

    @TypeConverter
    fun toDimensions(dimensionsString: String?): Dimensions? {
        return Gson().fromJson(dimensionsString, object : TypeToken<Dimensions>() {}.type)
    }

    @TypeConverter
    fun fromMeta(meta: Meta?): String? {
        return Gson().toJson(meta)
    }

    @TypeConverter
    fun toMeta(metaString: String?): Meta? {
        return Gson().fromJson(metaString, object : TypeToken<Meta>() {}.type)
    }

    @TypeConverter
    fun fromReviewList(reviews: List<Review>?): String? {
        return Gson().toJson(reviews)
    }

    @TypeConverter
    fun toReviewList(reviewString: String?): List<Review>? {
        return Gson().fromJson(reviewString, object : TypeToken<List<Review>>() {}.type)
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }
}