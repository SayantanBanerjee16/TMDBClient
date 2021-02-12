package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.api.TMDBService
import com.sayantanbanerjee.tmdbclient.data.model.Movie.MovieList
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import retrofit2.Response

// Implementation of the Movie Remote Data Source Interface.
// That is, it makes a retrofit call to the server which returns movie list as response.
class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) :
    MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
}
