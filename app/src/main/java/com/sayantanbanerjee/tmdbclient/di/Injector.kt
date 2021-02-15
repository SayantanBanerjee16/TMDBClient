package com.sayantanbanerjee.tmdbclient.di

import com.sayantanbanerjee.tmdbclient.di.artist.ArtistSubComponent
import com.sayantanbanerjee.tmdbclient.di.movie.MovieSubComponent
import com.sayantanbanerjee.tmdbclient.di.tvshow.TvShowSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent

}
