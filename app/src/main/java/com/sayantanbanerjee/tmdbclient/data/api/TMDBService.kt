package com.sayantanbanerjee.tmdbclient.data.api

import com.sayantanbanerjee.tmdbclient.data.model.Artist.ArtistList
import com.sayantanbanerjee.tmdbclient.data.model.Movie.MovieList
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit Instance which define the endpoints through which data is fetched from the API.
interface TMDBService {

    // Fetching the popular Movies.
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieList>

    // Fetching the popular TV shows.
    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key") apiKey: String): Response<TvShowList>

    // Fetching the popular Artists.
    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<ArtistList>

}
