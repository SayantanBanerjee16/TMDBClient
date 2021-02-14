package com.sayantanbanerjee.tmdbclient.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.databinding.ActivityTvShowBinding

// Screen which displays the list of all the TvShows.
class TvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
    }
}
