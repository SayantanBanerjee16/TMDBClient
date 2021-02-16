package com.sayantanbanerjee.tmdbclient.presentation.artist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
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
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.databinding.ActivityArtistBinding
import com.sayantanbanerjee.tmdbclient.di.Injector
import com.sayantanbanerjee.tmdbclient.utilities.CheckNetworkConnection
import javax.inject.Inject

// Screen which displays the list of all the Artist.
class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory

    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var binding: ActivityArtistBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the activity to use Data Binding.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)

        // Inject the Dagger sub-component.
        (application as Injector).createArtistSubComponent()
            .inject(this)

        // Initialize the view model.
        artistViewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.sayantanbanerjee.tmdbclient",
            Context.MODE_PRIVATE
        )

        initRecyclerView()
        getArtistData()
    }

    // Initialize the recycler view.
    private fun initRecyclerView() {
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter
    }

    // Check if Artist data pre-fetched or not.
    // If pre-fetched already, call the list from the repository, here, Database/Cache calling.
    // If not, first check for the network connection.
    // If valid connectivity found, call the list from the repository, here, API calling.
    private fun getArtistData() {
        if (sharedPreferences.getBoolean("first_time_artist_data_fetch", true)) {
            if (CheckNetworkConnection.checkNetwork(this)) {
                displayPopularArtist()
                sharedPreferences.edit().putBoolean("first_time_artist_data_fetch", false).apply()
            } else {
                Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            displayPopularArtist()
        }
    }

    // Call the view model which returns the artist list as a live data.
    // And then pass the list to the adapter.
    private fun displayPopularArtist() {
        binding.artistProgressBar.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtists()
        responseLiveData.observe(this, Observer {
            binding.artistProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No artists fetched!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Setting the Menu item which sets the Update Artist option.
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
                    updatePopularArtist()
                    sharedPreferences.edit().putBoolean("first_time_artist_data_fetch", false)
                        .apply()
                } else {
                    Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Call the view model which returns the updated artist list as a live data.
    // And then pass the updated list to the adapter.
    private fun updatePopularArtist() {
        binding.artistProgressBar.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.updateArtists()
        responseLiveData.observe(this, Observer {
            binding.artistProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No artists fetched!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
