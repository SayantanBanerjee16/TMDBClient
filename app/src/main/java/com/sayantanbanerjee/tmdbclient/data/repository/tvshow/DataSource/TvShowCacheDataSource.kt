package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow

// Interface defining the methods in the TvShow Cache Data Source.
interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache(): List<TvShow>
    suspend fun saveTvShowsToCache(tvshows: List<TvShow>)
}
