package com.sayantanbanerjee.tmdbclient.domain.repository

import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist

// Interface defining the methods about the task performed with the "Artists."
// This repository will be implemented in the Data Layer.
interface ArtistRepository {

    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?

}
