package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB() : List<Movie>
    suspend fun saveMoviesToDB(movies : List<Movie>)
    suspend fun clearAll()
}
