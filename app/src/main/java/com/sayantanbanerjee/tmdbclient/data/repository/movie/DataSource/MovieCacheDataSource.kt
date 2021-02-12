package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie

// Interface defining the methods in the Movie Cache Data Source.
interface MovieCacheDataSource {
    suspend fun getMoviesFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies: List<Movie>)
}
