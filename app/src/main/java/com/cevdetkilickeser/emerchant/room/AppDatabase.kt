package com.cevdetkilickeser.emerchant.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cevdetkilickeser.emerchant.data.model.like.Like
import com.cevdetkilickeser.emerchant.utils.RoomTypeConverter

@Database(entities = [Like::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLikeDao(): LikeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}