package com.sayantanbanerjee.tmdbclient.di.tvshow

import com.sayantanbanerjee.tmdbclient.domain.usecase.GetTvShowUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateTvShowUseCase
import com.sayantanbanerjee.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowUseCase: GetTvShowUseCase,
        updateTvShowUseCase: UpdateTvShowUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(
            getTvShowUseCase,
            updateTvShowUseCase
        )
    }
}
