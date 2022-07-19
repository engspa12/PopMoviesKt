package com.example.dbm.popularmovieskt.di

import android.content.Context
import androidx.room.Room
import com.example.dbm.popularmovieskt.data.local.datasource.MoviesDao
import com.example.dbm.popularmovieskt.data.local.datasource.MoviesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesRoomDatabase {
        return Room.databaseBuilder(
            context,
            MoviesRoomDatabase::class.java,
            "MoviesDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: MoviesRoomDatabase): MoviesDao {
        return database.moviesDao()
    }
}