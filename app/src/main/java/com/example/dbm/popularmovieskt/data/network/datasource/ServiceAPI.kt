package com.example.dbm.popularmovieskt.data.network.datasource

import com.example.dbm.popularmovieskt.BuildConfig
import com.example.dbm.popularmovieskt.data.network.model.MoviesResponse
import com.example.dbm.popularmovieskt.data.network.model.ReviewsResponse
import com.example.dbm.popularmovieskt.data.network.model.TrailersResponse
import com.example.dbm.popularmovieskt.global.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET("{sort_value}")
    suspend fun getMovies(
        @Path("sort_value") sortValue: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE,
        @Query("page") page: Int): MoviesResponse

    @GET("{movie_id}/videos")
    suspend fun getTrailers(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE): TrailersResponse

    @GET("{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = Constants.LANGUAGE): ReviewsResponse

}