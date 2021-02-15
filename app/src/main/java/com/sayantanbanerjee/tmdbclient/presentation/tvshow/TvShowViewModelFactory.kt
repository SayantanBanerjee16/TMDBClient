package com.sayantanbanerjee.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetTvShowUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateTvShowUseCase

// Tv Show View Model Factory Class.
class TvShowViewModelFactory(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShowUseCase, updateTvShowUseCase) as T
    }
}
