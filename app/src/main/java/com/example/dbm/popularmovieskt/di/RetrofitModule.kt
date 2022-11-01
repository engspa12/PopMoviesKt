package com.example.dbm.popularmovieskt.di

import com.example.dbm.popularmovieskt.data.network.datasource.ServiceAPI
import com.example.dbm.popularmovieskt.global.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, mosh: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(mosh))
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesService(mosh: Moshi): ServiceAPI {
        return provideRetrofit(Constants.BASE_URL, mosh).create(ServiceAPI::class.java)
    }

}