package com.sayantanbanerjee.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import com.sayantanbanerjee.tmdbclient.R
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.tmdbclient.databinding.ActivityMovieBinding

// Screen which displays the list of all the movies.
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }
}
