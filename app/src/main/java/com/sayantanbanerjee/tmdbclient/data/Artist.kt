package com.sayantanbanerjee.tmdbclient.data

import com.google.gson.annotations.SerializedName

// Data class which holds the items for popular Artists.
data class Artist(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)
