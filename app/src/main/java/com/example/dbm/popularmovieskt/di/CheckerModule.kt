package com.example.dbm.popularmovieskt.di

import android.content.Context
import com.example.dbm.popularmovieskt.domain.util.IConnectionChecker
import com.example.dbm.popularmovieskt.domain.util.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CheckerModule {

    @Provides
    fun provideConnectionChecker(@ApplicationContext appContext: Context): IConnectionChecker {
        return ConnectionChecker(appContext)
    }
}