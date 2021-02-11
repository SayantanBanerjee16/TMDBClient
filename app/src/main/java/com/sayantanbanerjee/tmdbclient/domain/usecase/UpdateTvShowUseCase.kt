package com.sayantanbanerjee.tmdbclient.domain.usecase

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow
import com.sayantanbanerjee.tmdbclient.domain.repository.TvShowRepository

// This use case is a part of CLean Architecture Principles.
// This use case will be called from the View Model.
// So that there is no direct dependency between Repository and View Model.

// This use case updates the list of TvShows in the database.
class UpdateTvShowUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShow()
}
