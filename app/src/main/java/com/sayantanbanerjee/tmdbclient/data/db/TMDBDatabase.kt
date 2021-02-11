package com.sayantanbanerjee.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow

// Creating a Database class which defines all of the entities and also it's related Dao interfaces.
@Database(
    entities = [Movie::class, Artist::class, TvShow::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun artistDao(): ArtistDao
    abstract fun tvDao(): TvShowDao
}
