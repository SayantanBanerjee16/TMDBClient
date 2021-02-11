package com.sayantanbanerjee.tmdbclient.data

import com.google.gson.annotations.SerializedName

// Data class which holds the list of items for popular Artists.
data class ArtistList(
    @SerializedName("results")
    val artists: List<Artist>,
)
