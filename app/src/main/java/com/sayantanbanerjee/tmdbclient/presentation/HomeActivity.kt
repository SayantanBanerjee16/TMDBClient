package com.sayantanbanerjee.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.databinding.ActivityHomeBinding
import com.sayantanbanerjee.tmdbclient.presentation.artist.ArtistActivity
import com.sayantanbanerjee.tmdbclient.presentation.movie.MovieActivity
import com.sayantanbanerjee.tmdbclient.presentation.tvshow.TvShowActivity

// Home screen of the application which holds three buttons, Movie Button, TvShow Button and Artists button.
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.movieButton.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
        }

        binding.tvButton.setOnClickListener {
            val intent = Intent(this, TvShowActivity::class.java)
            startActivity(intent)
        }

        binding.artistsButton.setOnClickListener {
            val intent = Intent(this, ArtistActivity::class.java)
            startActivity(intent)
        }
    }
}
