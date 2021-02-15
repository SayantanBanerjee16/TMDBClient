package com.sayantanbanerjee.tmdbclient.data.model.TvShow

import com.google.gson.annotations.SerializedName

// Data class which holds the list of items for popular TV Shows.
data class TvShowList(
    @SerializedName("results")
    val tvShows: List<TvShow>
)
