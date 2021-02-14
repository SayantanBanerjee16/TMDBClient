package com.sayantanbanerjee.tmdbclient.di

import com.sayantanbanerjee.tmdbclient.data.db.ArtistDao
import com.sayantanbanerjee.tmdbclient.data.db.MovieDao
import com.sayantanbanerjee.tmdbclient.data.db.TvShowDao
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl.ArtistLocalDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl.MovieLocalDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providesMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun providesArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDao)
    }

    @Singleton
    @Provides
    fun providesTvLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
        return TvShowLocalDataSourceImpl(tvShowDao)
    }
}
