package com.cevdetkilickeser.emerchant.di

import com.cevdetkilickeser.emerchant.data.repo.Repository
import com.cevdetkilickeser.emerchant.data.repo.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

}