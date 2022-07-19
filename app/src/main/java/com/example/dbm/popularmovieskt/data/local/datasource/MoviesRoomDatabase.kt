package com.example.dbm.popularmovieskt.data.local.datasource

import androidx.room.*
import com.example.dbm.popularmovieskt.data.local.model.MovieCache
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        MovieCache::class
    ], version = 1, exportSchema = false)
abstract class MoviesRoomDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

@Dao
interface MoviesDao{

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovies(): List<MovieCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavoriteMovie(vararg movieCache: MovieCache)

    @Query("DELETE FROM favorite_movies WHERE movieId = :movieId")
    fun deleteFavoriteMovie(movieId: Int)

}