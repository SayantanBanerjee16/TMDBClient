package com.sayantanbanerjee.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetMoviesUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateMoviesUseCase

// Movie View Model Factory Class.
class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
    }
}
