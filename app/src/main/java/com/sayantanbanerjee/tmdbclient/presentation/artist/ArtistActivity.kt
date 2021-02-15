package com.sayantanbanerjee.tmdbclient.presentation.artist

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
import javax.inject.Inject

// Screen which displays the list of all the Artist.
class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ArtistViewModelFactory

    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter
    private lateinit var binding: ActivityArtistBinding

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

        initRecyclerView()
    }

    // Initialize the recycler view.
    private fun initRecyclerView() {
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter
        displayPopularArtist()
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
                updatePopularArtist()
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
