package com.sayantanbanerjee.tmdbclient.domain.usecase

import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.domain.repository.MovieRepository

// This use case is a part of CLean Architecture Principles.
// This use case will be called from the View Model.
// So that there is no direct dependency between Repository and View Model.

// This use case return the list of Movies from the database.
class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}
