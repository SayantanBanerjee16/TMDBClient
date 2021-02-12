package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow

// Interface defining the methods in the TvShow Local Data Source, i.e, Database.
interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB() : List<TvShow>
    suspend fun saveTvShowsToDB(tvshows : List<TvShow>)
    suspend fun clearAll()
}
