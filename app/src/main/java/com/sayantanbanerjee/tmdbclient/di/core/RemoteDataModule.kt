package com.sayantanbanerjee.tmdbclient.di.core

import com.sayantanbanerjee.tmdbclient.data.api.TMDBService
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl.ArtistRemoteDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl.MovieRemoteDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Provides
    @Singleton
    fun providesMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

    @Provides
    @Singleton
    fun providesArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

    @Provides
    @Singleton
    fun providesTvRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

}
