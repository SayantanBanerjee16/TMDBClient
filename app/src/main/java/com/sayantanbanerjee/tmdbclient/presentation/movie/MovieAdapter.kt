package com.sayantanbanerjee.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.data.model.Movie.Movie
import com.sayantanbanerjee.tmdbclient.databinding.ListItemBinding

class MovieAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
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
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}

class RecyclerViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.titleTextView.text = movie.title
        binding.descriptionTextView.text = movie.overview
        val posterURL = "https://image.tmdb.org/t/p/w500" + movie.posterPath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }

}
