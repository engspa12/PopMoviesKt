package com.example.dbm.popularmovieskt.util

sealed class ResultWrapper<T>{
    data class Success<T>(val data: T): ResultWrapper<T>()
    data class Failure<T>(val errorMessage: String? = null): ResultWrapper<T>()
}