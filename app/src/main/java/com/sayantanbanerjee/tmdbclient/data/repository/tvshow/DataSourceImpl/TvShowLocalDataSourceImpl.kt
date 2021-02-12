package com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSourceImpl

import com.sayantanbanerjee.tmdbclient.data.db.TvShowDao
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Implementation of the TvShows Local Data Source Interface.
// It takes TvShowDao as a parameter, which allows us to interact with the local TvShows entity.
// The methods includes :-
//      Saving TvShows into the entity.
//      Getting the TvShows from the entity.
//      Clearing all the TvShows in the entity.
class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {

    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getAllTvShows()
    }

    override suspend fun saveTvShowsToDB(tvshows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvshows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}
