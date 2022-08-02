package com.example.dbm.popularmovieskt.di

import android.content.Context
import com.example.dbm.popularmovieskt.util.IValidator
import com.example.dbm.popularmovieskt.util.Validator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ValidatorModule {

    @Provides
    fun provideValidator(@ApplicationContext appContext: Context): IValidator {
        return Validator(appContext)
    }
}