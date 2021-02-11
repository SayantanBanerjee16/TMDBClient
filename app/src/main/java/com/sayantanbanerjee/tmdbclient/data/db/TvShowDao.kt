package com.sayantanbanerjee.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow

// DAO interface for the "popular_tvShows" table defined in the [TvShow.kt]
// It got 3 methods :-
//      First method to insert a list of Tv Shows into the database.
//      Second method to delete all the Tv Shows from the database.
//      Third method is to fetch all the Tv Shows from the database.
@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvshows: List<TvShow>)

    @Query("DELETE FROM popular_tvShows")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM popular_tvShows")
    suspend fun getAllTvShows() : List<TvShow>
}
