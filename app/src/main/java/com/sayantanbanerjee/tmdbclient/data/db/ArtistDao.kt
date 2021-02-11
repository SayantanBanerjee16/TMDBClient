package com.sayantanbanerjee.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist

// DAO interface for the "popular_artists" table defined in the [Artist.kt]
// It got 3 methods :-
//      First method to insert a list of artists into the database.
//      Second method to delete all the artists from the database.
//      Third method is to fetch all the artists from the database.
@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_artists")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM popular_artists")
    suspend fun getAllArtists()
}
