package com.sayantanbanerjee.tmdbclient.data.model.Movie

import com.google.gson.annotations.SerializedName

// Data class which holds the list of items for popular Movies.
data class MovieList(
    @SerializedName("movies")
    val movies: List<Movie>,
)
