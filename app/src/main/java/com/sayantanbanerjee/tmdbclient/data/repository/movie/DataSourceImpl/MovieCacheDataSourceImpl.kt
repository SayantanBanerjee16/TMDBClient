package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource

// Implementation of the Movie Cache Data Source Interface.
// We are temporarily storing a list of movies in an ArrayList, and fetching it when required.
class MovieCacheDataSourceImpl : MovieCacheDataSource {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }
}
