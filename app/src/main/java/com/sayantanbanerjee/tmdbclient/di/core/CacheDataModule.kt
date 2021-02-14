package com.sayantanbanerjee.tmdbclient.di.core

import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSourceImpl.ArtistCacheDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl.MovieCacheDataSourceImpl
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Provides
    @Singleton
    fun providesMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun providesArtistCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }

    @Provides
    @Singleton
    fun providesTvCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }
}
