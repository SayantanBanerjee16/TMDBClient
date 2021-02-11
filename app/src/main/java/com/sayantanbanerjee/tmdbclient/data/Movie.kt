package com.sayantanbanerjee.tmdbclient.data

import com.google.gson.annotations.SerializedName

// Data class which holds the items for popular Movies.
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
)
