package com.sayantanbanerjee.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetTvShowUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateTvShowUseCase

// TvShows View Model for the TvShow Activity.
// These view model call the respective Use Cases which returns the List of TvShows.
// Now these are emitted as live Data which are observed in the activity file.
class TvShowViewModel(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModel() {

    fun getTvShows() = liveData {
        val tvShowList = getTvShowUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShows() = liveData {
        val updatedTvShowList = updateTvShowUseCase.execute()
        emit(updatedTvShowList)
    }
}
