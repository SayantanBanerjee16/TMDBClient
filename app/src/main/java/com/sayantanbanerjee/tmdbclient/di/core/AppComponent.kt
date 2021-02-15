package com.sayantanbanerjee.tmdbclient.di.core

import com.sayantanbanerjee.tmdbclient.di.artist.ArtistSubComponent
import com.sayantanbanerjee.tmdbclient.di.movie.MovieSubComponent
import com.sayantanbanerjee.tmdbclient.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    AppModule::class,
    NetModule::class,
    DatabaseModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    RemoteDataModule::class,
    LocalDataModule::class,
    CacheDataModule::class
])
interface AppComponent {
    fun movieSubComponent() : MovieSubComponent.Factory
    fun tvShowSubComponent() : TvShowSubComponent.Factory
    fun artistSubComponent() : ArtistSubComponent.Factory
}
