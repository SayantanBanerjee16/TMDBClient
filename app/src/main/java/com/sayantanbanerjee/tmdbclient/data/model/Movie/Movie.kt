package com.sayantanbanerjee.tmdbclient.data.model.Movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Data class which holds the items for popular Movies.
// This data class holds the purpose both for Room Database as well as when calling its related API.
@Entity(tableName = "popular_movies")
data class Movie(
    @PrimaryKey
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
