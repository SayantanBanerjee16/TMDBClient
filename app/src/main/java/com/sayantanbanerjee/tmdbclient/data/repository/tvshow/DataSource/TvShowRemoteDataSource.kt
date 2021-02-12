package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShowList
import retrofit2.Response

// Interface defining the methods in the TvShow Remote Data Source, i.e, Server.
interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}
