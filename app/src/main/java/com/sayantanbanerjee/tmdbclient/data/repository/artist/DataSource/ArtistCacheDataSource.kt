package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist

// Interface defining the methods in the Artist Cache Data Source.
interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache(): List<Artist>
    suspend fun saveArtistsToCache(artists: List<Artist>)
}
