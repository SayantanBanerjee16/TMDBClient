package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.api.TMDBService
import com.sayantanbanerjee.tmdbclient.data.model.Artist.ArtistList
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import retrofit2.Response

// Implementation of the Artist Remote Data Source Interface.
// That is, it makes a retrofit call to the server which returns artist list as response.
class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) :
    ArtistRemoteDataSource {
    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)
}
