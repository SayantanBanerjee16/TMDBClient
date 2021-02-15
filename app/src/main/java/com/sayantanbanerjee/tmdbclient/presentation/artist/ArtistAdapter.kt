package com.sayantanbanerjee.tmdbclient.presentation.artist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.data.model.Artist.Artist
import com.sayantanbanerjee.tmdbclient.databinding.ListItemBinding

class ArtistAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val artistList = ArrayList<Artist>()

    fun setList(artists: List<Artist>) {
        artistList.clear()
        artistList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(artistList[position])
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

}

class RecyclerViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(artist: Artist) {
        binding.titleTextView.text = artist.name
        binding.descriptionTextView.text = "Popularity level :- " + artist.popularity.toString()
        val posterURL = "https://image.tmdb.org/t/p/w500" + artist.profilePath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }

}
