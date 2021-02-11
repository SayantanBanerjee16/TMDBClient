package com.sayantanbanerjee.tmdbclient.data.repository.movie

import android.util.Log
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import com.sayantanbanerjee.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovie = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovie)
        movieCacheDataSource.saveMoviesToCache(newListOfMovie)
        return newListOfMovie
    }

    suspend fun getMoviesFromAPI() : List<Movie>{
        lateinit var movieList : List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val responseBody = response.body()
            if(responseBody != null){
                movieList = responseBody.movies
            }
        }catch (exp : Exception){
            Log.i("GET MOVIES FROM API", exp.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB() : List<Movie>{
        lateinit var movieList : List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        }catch (exp : Exception){
            Log.i("GET MOVIES FROM DB", exp.toString())
        }
        return if(movieList.size >= 0){
            movieList
        }else{
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
            movieList
        }
    }

    suspend fun getMoviesFromCache() : List<Movie>{
        lateinit var movieList : List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        }catch (exp : Exception){
            Log.i("GET MOVIES FROM DB", exp.toString())
        }
        return if(movieList.size >= 0){
            movieList
        }else{
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
            movieList
        }
    }
}
