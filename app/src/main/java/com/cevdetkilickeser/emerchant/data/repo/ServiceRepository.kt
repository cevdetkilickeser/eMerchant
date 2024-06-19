package com.cevdetkilickeser.emerchant.data.repo

import com.cevdetkilickeser.emerchant.data.datasource.ServiceDataSource
import com.cevdetkilickeser.emerchant.data.entity.User

class ServiceRepository(val datasource: ServiceDataSource) {

    suspend fun login(username: String, password: String): User? =
        datasource.login(username, password)

}