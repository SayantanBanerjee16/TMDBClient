package com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.db.MovieDao
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.data.repository.movie.DataSource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Implementation of the Movie Local Data Source Interface.
// It takes MovieDao as a parameter, which allows us to interact with the local Movie database.
// THe methods includes :-
//      Saving movies from the database.
//      Getting the movies from the database.
//      Clearing all the movies in the database.
class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}
