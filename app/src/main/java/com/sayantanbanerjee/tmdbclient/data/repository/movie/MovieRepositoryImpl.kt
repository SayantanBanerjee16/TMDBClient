package com.sayantanbanerjee.tmdbclient.data.repository.movie

import android.util.Log
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieRemoteDataSource
import com.sayantanbanerjee.tmdbclient.domain.repository.MovieRepository

// Implementation of the Movie Repository Interface, which is defined in [Domain/repository/MovieRepository].
// This methods are directly called from the Use Cases.
// There are majorly 2 methods,
//      One is to fetch the movies.
//          We did this by first referring to the Cache Data Source,
//          If not present in Cache, then we call the Local Data Source, i.e, database and store the list in the Cache.
//          If not present in Local database also, then we make a retrofit call to the server, and store the list in the Local Database and Cache Data source.
//      Other is to update the movies list.
//          First we make a retrofit call to the server, which returns a list of the updated movies.
//          Then we store this updated list in the local database, and also in the Cache Data source.


// For this, we first injected the objects of all the Three Data source as a constructor.
// And use them to serve our purpose.
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

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val responseBody = response.body()
            if (responseBody != null) {
                movieList = responseBody.movies
            }
        } catch (exp: Exception) {
            Log.i("GET MOVIES FROM API", exp.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exp: Exception) {
            Log.i("GET MOVIES FROM DB", exp.toString())
        }
        return if (movieList.isNotEmpty()) {
            movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movieList)
            movieList
        }
    }

    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exp: Exception) {
            Log.i("GET MOVIES FROM CACHE", exp.toString())
        }
        return if (movieList.isNotEmpty()) {
            movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movieList)
            movieList
        }
    }
}
