package com.sayantanbanerjee.tmdbclient.data.repository.artist

import android.util.Log
import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistCacheDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistLocalDataSource
import com.sayantanbanerjee.tmdbclient.data.repository.artist.DataSource.ArtistRemoteDataSource
import com.sayantanbanerjee.tmdbclient.domain.repository.ArtistRepository

// Implementation of the Artist Repository Interface, which is defined in [Domain/repository/ArtistRepository].
// This repository methods are directly called from the Use Cases.
// There are majorly 2 methods,
//      One is to fetch the artists.
//          We did this by first referring to the Cache Data Source,
//          If not present in Cache, then we call the Local Data Source, i.e, database and store the list in the Cache.
//          If not present in Local database also, then we make a retrofit call to the server, and store the list in the Local Database and Cache Data source.
//      Other is to update the artists list.
//          First we make a retrofit call to the server, which returns a list of the updated movies.
//          Then we store this updated list in the local database, and also in the Cache Data source.


// For this, we first injected the objects of all the Three Data source as a constructor.
// And use them to serve our purpose.
class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource,
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistsFromAPI() : List<Artist>{
        lateinit var artistList : List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val responseBody = response.body()
            if(responseBody != null){
                artistList = responseBody.artists
            }
        }catch (exp : Exception){
            Log.i("GET ARTIST FROM API", exp.toString())
        }
        return artistList
    }

    suspend fun getArtistFromDB() : List<Artist>{
        lateinit var artistList : List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        }catch (exp : Exception){
            Log.i("GET ARTIST FROM DB", exp.toString())
        }
        return if(artistList.size >= 0){
            artistList
        }else{
            artistList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistList)
            artistList
        }
    }

    suspend fun getArtistsFromCache() : List<Artist>{
        lateinit var artistList : List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        }catch (exp : Exception){
            Log.i("GET ARTIST FROM CACHE", exp.toString())
        }
        return if(artistList.size >= 0){
            artistList
        }else{
            artistList = getArtistFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
            artistList
        }
    }
}
