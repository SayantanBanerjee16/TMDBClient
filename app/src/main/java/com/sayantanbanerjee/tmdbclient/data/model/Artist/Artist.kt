package com.sayantanbanerjee.tmdbclient.data.model.Artist

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Data class which holds the items for popular Artists.
// This data class holds the purpose both for Room Database as well as when calling its related API.
@Entity(tableName = "popular_artists")
data class Artist(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)
