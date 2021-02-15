package com.sayantanbanerjee.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sayantanbanerjee.tmdbclient.R
import com.sayantanbanerjee.tmdbclient.data.model.TvShow.TvShow
import com.sayantanbanerjee.tmdbclient.databinding.ListItemBinding

class TvShowAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val tvshowList = ArrayList<TvShow>()

    fun setList(tvshows: List<TvShow>) {
        tvshowList.clear()
        tvshowList.addAll(tvshows)
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
        holder.bind(tvshowList[position])
    }

    override fun getItemCount(): Int {
        return tvshowList.size
    }

}

class RecyclerViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(tvshow: TvShow) {
        binding.titleTextView.text = tvshow.name
        binding.descriptionTextView.text = tvshow.overview
        val posterURL = "https://image.tmdb.org/t/p/w500" + tvshow.posterPath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }

}
