package com.sayantanbanerjee.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.databinding.ActivityArtistBinding

// Screen which displays the list of all the Artist.
class ArtistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
    }
}
