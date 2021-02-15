package com.sayantanbanerjee.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.tmdbclient.R
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sayantanbanerjee.tmdbclient.databinding.ActivityMovieBinding
import com.sayantanbanerjee.tmdbclient.di.Injector
import javax.inject.Inject

// Screen which displays the list of all the movies.
class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            Log.i("******MOVIE_ACTIVITY**", it.toString())
        })
    }
}
