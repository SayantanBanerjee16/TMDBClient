package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.api.TMDBService
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShowList
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowRemoteDataSource
import retrofit2.Response

// Implementation of the Tv Shows Remote Data Source Interface.
// That is, it makes a retrofit call to the server which returns TvShow list as response.
class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) :
    TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)
}
