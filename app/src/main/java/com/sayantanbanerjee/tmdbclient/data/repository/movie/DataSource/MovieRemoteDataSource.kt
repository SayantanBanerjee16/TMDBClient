package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies() : Response<MovieList>
}
