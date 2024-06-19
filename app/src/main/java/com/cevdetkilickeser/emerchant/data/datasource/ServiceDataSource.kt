package com.cevdetkilickeser.emerchant.data.datasource

import com.cevdetkilickeser.emerchant.retrofit.ServiceDao

class ServiceDataSource(val sdao: ServiceDao) {

    suspend fun login(email: String, password: String) = sdao.login(email, password)

}