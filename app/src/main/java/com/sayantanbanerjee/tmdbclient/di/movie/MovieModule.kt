package com.sayantanbanerjee.tmdbclient.di.movie

import com.sayantanbanerjee.tmdbclient.domain.usecase.GetMoviesUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.sayantanbanerjee.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMoviesUseCase,
            updateMoviesUseCase
        )
    }
}
