package com.sayantanbanerjee.tmdbclient.di.artist

import com.sayantanbanerjee.tmdbclient.domain.usecase.GetArtistUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateArtistUseCase
import com.sayantanbanerjee.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistUseCase: GetArtistUseCase,
        updateArtistUseCase: UpdateArtistUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            getArtistUseCase,
            updateArtistUseCase
        )
    }
}
