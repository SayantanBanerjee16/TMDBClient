package com.sayantanbanerjee.tmdbclient.data.repository.tvshow

import android.util.Log
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.tvshow.DataSource.TvShowRemoteDataSource
import com.sayantanbanerjee.tmdbclient.domain.repository.TvShowRepository

// Implementation of the TvShow Repository Interface, which is defined in [Domain/repository/TvShowRepository].
// This repository methods are directly called from the Use Cases.
// There are majorly 2 methods,
//      One is to fetch the TvShows.
//          We did this by first referring to the Cache Data Source,
//          If not present in Cache, then we call the Local Data Source, i.e, database and store the list in the Cache.
//          If not present in Local database also, then we make a retrofit call to the server, and store the list in the Local Database and Cache Data source.
//      Other is to update the TvShows list.
//          First we make a retrofit call to the server, which returns a list of the updated movies.
//          Then we store this updated list in the local database, and also in the Cache Data source.


// For this, we first injected the objects of all the Three Data source as a constructor.
// And use them to serve our purpose.
class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource,
) : TvShowRepository {

    override suspend fun getTvShow(): List<TvShow>? {
        return getTvShowFromCache()
    }

    override suspend fun updateTvShow(): List<TvShow>? {
        val newListOfTvShows = getTvShowFromAPI()
        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowFromAPI() : List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val responseBody = response.body()
            if(responseBody != null){
                tvShowList = responseBody.tvShows
            }
        }catch (exp : Exception){
            Log.i("GET TV SHOW FROM API", exp.toString())
        }
        return tvShowList
    }

    suspend fun getTvShowFromDB() : List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
        }catch (exp : Exception){
            Log.i("GET TV SHOW FROM DB", exp.toString())
        }
        return if(tvShowList.size >= 0){
            tvShowList
        }else{
            tvShowList = getTvShowFromAPI()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
            tvShowList
        }
    }

    suspend fun getTvShowFromCache() : List<TvShow>{
        lateinit var tvShowList : List<TvShow>
        try {
            tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
        }catch (exp : Exception){
            Log.i("GET TV SHOW FROM CACHE", exp.toString())
        }
        return if(tvShowList.size >= 0){
            tvShowList
        }else{
            tvShowList = getTvShowFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
            tvShowList
        }
    }
}
