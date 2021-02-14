package com.sayantanbanerjee.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetArtistUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateArtistUseCase

class ArtistViewModelFactory(
    private val getArtistUseCase: GetArtistUseCase,
    private val updateArtistUseCase: UpdateArtistUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistViewModel(getArtistUseCase, updateArtistUseCase) as T
    }
}
