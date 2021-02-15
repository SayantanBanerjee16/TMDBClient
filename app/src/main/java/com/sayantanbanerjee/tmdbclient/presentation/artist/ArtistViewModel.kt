package com.sayantanbanerjee.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sayantanbanerjee.tmdbclient.domain.usecase.GetArtistUseCase
import com.sayantanbanerjee.tmdbclient.domain.usecase.UpdateArtistUseCase

// Artist View Model for the Artist Activity.
// These view model call the respective Use Cases which returns the List of Artist.
// Now these are emitted as live Data which are observed in the activity file.
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
