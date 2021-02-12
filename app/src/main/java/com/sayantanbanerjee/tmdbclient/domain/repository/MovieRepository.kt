package com.sayantanbanerjee.tmdbclient.domain.repository

import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie

// Interface defining the methods about the task performed with the "Movies."
// This repository will be implemented in the Data Layer.
interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?

}
