package com.sayantanbanerjee.tmdbclient.domain.usecase

import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.domain.repository.ArtistRepository

// This use case is a part of CLean Architecture Principles.
// This use case will be called from the View Model.
// So that there is no direct dependency between Repository and View Model.

// This use case return the list of Artists from the database.
class GetArtistUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}
