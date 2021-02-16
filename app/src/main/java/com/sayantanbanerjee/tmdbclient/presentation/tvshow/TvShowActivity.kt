package com.sayantanbanerjee.tmdbclient.presentation.tvshow

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
import com.sayantanbanerjee.tmdbclient.databinding.ActivityTvShowBinding
import com.sayantanbanerjee.tmdbclient.di.Injector
import com.sayantanbanerjee.tmdbclient.utilities.CheckNetworkConnection
import javax.inject.Inject

// Screen which displays the list of all the TvShows.
class TvShowActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: TvShowViewModelFactory

    private lateinit var tvshowViewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private lateinit var binding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the activity to use Data Binding.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        // Inject the Dagger sub-component.
        (application as Injector).createTvShowSubComponent()
            .inject(this)

        // Initialize the view model.
        tvshowViewModel = ViewModelProvider(this, factory)
            .get(TvShowViewModel::class.java)

        initRecyclerView()
    }

    // Initialize the recycler view.
    private fun initRecyclerView() {
        binding.tvshowRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.tvshowRecyclerView.adapter = adapter
        if (CheckNetworkConnection.checkNetwork(this)) {
            displayPopularTvShows()
        } else {
            Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Call the view model which returns the TvShows list as a live data.
    // And then pass the list to the adapter.
    private fun displayPopularTvShows() {
        binding.tvshowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvshowViewModel.getTvShows()
        responseLiveData.observe(this, Observer {
            binding.tvshowProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No tv shows fetched!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    // Setting the Menu item which sets the Update TvShow option.
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
                    updatePopularTvShows()
                } else {
                    Toast.makeText(this, "Network connection is not available!", Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Call the view model which returns the updated TvShows list as a live data.
    // And then pass the updated list to the adapter.
    private fun updatePopularTvShows() {
        binding.tvshowProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvshowViewModel.updateTvShows()
        responseLiveData.observe(this, Observer {
            binding.tvshowProgressBar.visibility = View.GONE
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No tv shows fetched!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
