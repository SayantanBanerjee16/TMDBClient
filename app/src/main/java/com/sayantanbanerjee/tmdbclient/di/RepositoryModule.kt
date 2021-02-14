package com.sayantanbanerjee.tmdbclient.di

import com.sayantanbanerjee.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowRemoteDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.sayantanbanerjee.tmdbclient.domain.repository.ArtistRepository
import com.sayantanbanerjee.tmdbclient.domain.repository.MovieRepository
import com.sayantanbanerjee.tmdbclient.domain.repository.TvShowRepository
import dagger.Provides
import javax.inject.Singleton

class RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDataSource,
            movieCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun providesArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository {
        return ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDataSource,
            artistCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun providesTvRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {
        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDataSource,
            tvShowCacheDataSource
        )
    }

}
