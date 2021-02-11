package com.sayantanbanerjee.tmdbclient.data

import com.google.gson.annotations.SerializedName

// Data class which holds the items for popular TV Shows.
data class TvShow(
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
)
