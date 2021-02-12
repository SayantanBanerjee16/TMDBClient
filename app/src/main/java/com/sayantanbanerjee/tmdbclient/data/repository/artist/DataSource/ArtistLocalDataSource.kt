package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource

import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist

// Interface defining the methods in the Artist Local Data Source, i.e, Database.
interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB() : List<Artist>
    suspend fun saveArtistsToDB(artists : List<Artist>)
    suspend fun clearAll()
}
