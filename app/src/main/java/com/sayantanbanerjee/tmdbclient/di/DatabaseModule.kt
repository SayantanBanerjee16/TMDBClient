package com.sayantanbanerjee.tmdbclient.di

import android.content.Context
import androidx.room.Room
import com.sayantanbanerjee.tmdbclient.data.db.ArtistDao
import com.sayantanbanerjee.tmdbclient.data.db.MovieDao
import com.sayantanbanerjee.tmdbclient.data.db.TMDBDatabase
import com.sayantanbanerjee.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesMovieDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient")
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {
        return tmdbDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun providesArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {
        return tmdbDatabase.artistDao()
    }

    @Provides
    @Singleton
    fun providesTvDao(tmdbDatabase: TMDBDatabase): TvShowDao {
        return tmdbDatabase.tvDao()
    }
}
