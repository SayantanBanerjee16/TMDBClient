package com.sayantanbanerjee.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetArtistUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateArtistUseCase

class ArtistViewModel(
    private val getArtistUseCase: GetArtistUseCase,
    private val updateArtistUseCase: UpdateArtistUseCase
) : ViewModel() {

    fun getArtists() = liveData {
        val artistList = getArtistUseCase.execute()
        emit(artistList)
    }

    fun updateArtists() = liveData {
        val updatedArtistList = updateArtistUseCase.execute()
        emit(updatedArtistList)
    }

}
