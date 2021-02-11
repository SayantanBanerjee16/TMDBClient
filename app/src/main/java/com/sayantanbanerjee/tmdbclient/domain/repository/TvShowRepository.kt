package com.sayantanbanerjee.tmdbclient.domain.repository

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow

// Interface defining the methods about the task to be performed in the "TvShow entity."
// This repository will be implemented in the Data Layer.
interface TvShowRepository {

    suspend fun getTvShow(): List<TvShow>?
    suspend fun updateTvShow(): List<TvShow>?

}
