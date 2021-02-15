package com.sayantanbanerjee.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetMoviesUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateMoviesUseCase

// Movie View Model for the Movie Activity.
// These view model call the respective Use Cases which returns the List of Movies.
// Now these are emitted as live Data which are observed in the activity file.
class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = liveData {
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMovies() = liveData {
        val updatedMovieList = updateMoviesUseCase.execute()
        emit(updatedMovieList)
    }
}
