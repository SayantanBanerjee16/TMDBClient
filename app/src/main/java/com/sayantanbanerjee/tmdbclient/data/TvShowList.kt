package com.sayantanbanerjee.tmdbclient.data

import com.google.gson.annotations.SerializedName

// Data class which holds the list of items for popular TV Shows.
data class TvShowList(
    @SerializedName("tvShows")
    val tvShows: List<TvShow>,
)
