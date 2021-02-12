package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource

// Implementation of the Artist Cache Data Source Interface.
// We are temporarily storing a list of artists in an ArrayList, and fetching it when required.
class ArtistCacheDataSourceImpl : ArtistCacheDataSource {

    private var artistList = ArrayList<Artist>()

    override suspend fun getArtistsFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artists)
    }
}
