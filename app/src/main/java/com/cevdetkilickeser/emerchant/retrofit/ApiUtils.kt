package com.cevdetkilickeser.emerchant.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "https://dummyjson.com/"

        fun getServiceDao(): ServiceDao {
            return RetrofitClient.getClient(BASE_URL).create(ServiceDao::class.java)
        }
    }
}