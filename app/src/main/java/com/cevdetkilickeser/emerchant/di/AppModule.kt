package com.cevdetkilickeser.emerchant.di

import com.cevdetkilickeser.emerchant.data.datasource.ServiceDataSource
import com.cevdetkilickeser.emerchant.data.repo.ServiceRepository
import com.cevdetkilickeser.emerchant.retrofit.ApiUtils
import com.cevdetkilickeser.emerchant.retrofit.ServiceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideServiceRepository(dataSource: ServiceDataSource): ServiceRepository {
        return ServiceRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideServiceDatasource(serviceDao: ServiceDao) : ServiceDataSource {
        return ServiceDataSource(serviceDao)
    }

    @Provides
    @Singleton
    fun provideServiceDao() : ServiceDao {
        return ApiUtils.getServiceDao()
    }
}