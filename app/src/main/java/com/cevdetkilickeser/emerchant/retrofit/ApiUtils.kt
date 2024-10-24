package com.cevdetkilickeser.emerchant.retrofit

class ApiUtils {
    companion object {
        private const val BASE_URL = "https://dummyjson.com/"

        fun getServiceDao(): ApiService {
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}