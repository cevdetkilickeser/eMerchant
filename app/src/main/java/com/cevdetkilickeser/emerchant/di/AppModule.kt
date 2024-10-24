package com.cevdetkilickeser.emerchant.di

import android.content.Context
import com.cevdetkilickeser.emerchant.data.datasource.DataSource
import com.cevdetkilickeser.emerchant.data.repo.Repository
import com.cevdetkilickeser.emerchant.retrofit.ApiService
import com.cevdetkilickeser.emerchant.retrofit.ApiUtils
import com.cevdetkilickeser.emerchant.room.AppDatabase
import com.cevdetkilickeser.emerchant.room.LikeDao
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideServiceRepository(
        dataSource: DataSource,
        firebaseDB: FirebaseFirestore
    ): Repository {
        return Repository(dataSource, firebaseDB)
    }

    @Provides
    @Singleton
    fun provideServiceDatasource(apiService: ApiService, likeDao: LikeDao): DataSource {
        return DataSource(apiService, likeDao)
    }

    @Provides
    @Singleton
    fun provideServiceDao(): ApiService {
        return ApiUtils.getServiceDao()
    }

    @Provides
    @Singleton
    fun provideLikeDao(@ApplicationContext context: Context): LikeDao {
        return AppDatabase.getDatabase(context).getLikeDao()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}