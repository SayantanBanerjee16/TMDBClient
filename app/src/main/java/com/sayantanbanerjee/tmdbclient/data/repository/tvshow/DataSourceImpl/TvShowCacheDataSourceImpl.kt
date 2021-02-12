package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource

// Implementation of the TvShows Cache Data Source Interface.
// We are temporarily storing a list of TvShows in an ArrayList, and fetching it when required.
class TvShowCacheDataSourceImpl : TvShowCacheDataSource {

    private var tvShowsList = ArrayList<TvShow>()

    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShowsList
    }

    override suspend fun saveTvShowsToCache(tvshows: List<TvShow>) {
        tvShowsList.clear()
        tvShowsList = ArrayList(tvshows)
    }
}
