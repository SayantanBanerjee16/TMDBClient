package com.sayantanbanerjee.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie

// DAO interface for the "popular_movies" table defined in the [Movie.kt]
// It got 3 methods :-
//      First method to insert a list of movies into the database.
//      Second method to delete all the movies from the database.
//      Third method is to fetch all the movies from the database.
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movies")
    suspend fun getAllMovies() : List<Movie>
}
