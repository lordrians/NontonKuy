package com.example.nontonkuy.data.source.local.entity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NontonKuyDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        @Volatile
        private var INSTANCE: NontonKuyDatabase? = null

        fun getInstance(mContext: Context): NontonKuyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(mContext.applicationContext, NontonKuyDatabase::class.java, "NontonKuy.db").build()
            }
    }

}