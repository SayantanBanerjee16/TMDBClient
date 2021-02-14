package com.sayantanbanerjee.tmdbclient.di.core

import android.content.Context
import com.sayantanbanerjee.tmdbclient.di.artist.ArtistSubComponent
import com.sayantanbanerjee.tmdbclient.di.movie.MovieSubComponent
import com.sayantanbanerjee.tmdbclient.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubComponent::class, ArtistSubComponent::class, TvShowSubComponent::class])
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}
