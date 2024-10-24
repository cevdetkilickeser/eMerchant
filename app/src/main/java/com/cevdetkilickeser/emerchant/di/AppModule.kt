package com.cevdetkilickeser.emerchant.di

import android.content.Context
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideServiceDao(): ApiService {
        return ApiUtils.getServiceDao()
    }

    @Provides
    fun provideLikeDao(@ApplicationContext context: Context): LikeDao {
        return AppDatabase.getDatabase(context).getLikeDao()
    }

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}