package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Artist.ArtistList
import retrofit2.Response

// Interface defining the methods in the Artists Remote Data Source, i.e, Server.
interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}
