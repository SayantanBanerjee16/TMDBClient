package com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.db.ArtistDao
import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Implementation of the Artist Local Data Source Interface.
// It takes ArtistDao as a parameter, which allows us to interact with the local Artist entity.
// The methods includes :-
//      Saving artists into the entity.
//      Getting the artists from the entity.
//      Clearing all the artists in the entity.
class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> {
        return artistDao.getAllArtists()
    }

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}
