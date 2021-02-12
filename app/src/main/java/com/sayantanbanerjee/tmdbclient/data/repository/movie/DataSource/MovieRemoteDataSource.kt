package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Movie.MovieList
import retrofit2.Response

// Interface defining the methods in the Movie Remote Data Source, i.e, Server.
interface MovieRemoteDataSource {
    suspend fun getMovies() : Response<MovieList>
}
