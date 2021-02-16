package com.sayantanbanerjee.tmdbclient.presentation.movie

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.tmdbclient.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sayantanbanerjee.tmdbclient.databinding.ActivityMovieBinding
import com.sayantanbanerjee.tmdbclient.di.Injector
import com.sayantanbanerjee.tmdbclient.utilities.CheckNetworkConnection
import javax.inject.Inject

// Screen which displays the list of all the movies.
class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the activity to use Data Binding.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        // Inject the Dagger sub-component.
        (application as Injector).createMovieSubComponent()
            .inject(this)

        // Initialize the view model.
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.sayantanbanerjee.tmdbclient",
            Context.MODE_PRIVATE
        )

        initRecyclerView()
        getMovieData()
    }

    // Initialize the recycler view.
    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
    }

    // Check if Movie data pre-fetched or not.
    // If pre-fetched already, call the list from the repository, here, Database/Cache calling.
    // If not, first check for the network connection.
    // If valid connectivity found, call the list from the repository, here, API calling.
    private fun getMovieData(){
        if (sharedPreferences.getBoolean("first_time_movie_data_fetch", true)) {
            if (CheckNetworkConnection.checkNetwork(this)) {
                displayPopularMovies()
                sharedPreferences.edit().putBoolean("first_time_movie_data_fetch", false).apply()
            } else {
                Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            // If data pre-fetched, fetch the list from the database/cache.
            displayPopularMovies()
        }
    }

    // Call the view model which returns the movie list as a live data.
    // And then pass the list to the adapter.
    private fun displayPopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            binding.movieProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No movies fetched!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Setting the Menu item which sets the Update Movie option.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    // Action to be performed when the updated button is clicked.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                if (CheckNetworkConnection.checkNetwork(this)) {
                    updatePopularMovies()
                    sharedPreferences.edit().putBoolean("first_time_movie_data_fetch", false).apply()
                } else {
                    Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Call the view model which returns the updated movies list as a live data.
    // And then pass the updated list to the adapter.
    private fun updatePopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.updateMovies()
        responseLiveData.observe(this, Observer {
            binding.movieProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No movies fetched!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
