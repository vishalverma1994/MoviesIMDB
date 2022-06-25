package com.moviesimdb.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviesimdb.R
import com.moviesimdb.databinding.AdapterMoviesListBinding
import com.moviesimdb.model.MoviesResult

class MoviesListAdapter(private val onItemClick: (Int) -> Unit): ListAdapter<MoviesResult, MoviesListAdapter.ViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    inner class ViewHolder(private val binding: AdapterMoviesListBinding, private val context: Context):
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.root.setOnClickListener {
                    onItemClick(absoluteAdapterPosition)
                }
            }

            fun bindViews(moviesResult: MoviesResult) {
                binding.tvMovieTitle.text = moviesResult.title
                binding.tvMovieDesc.text = moviesResult.overview
                val imageUrl = context.getString(R.string.poster_url, moviesResult.poster_path)
                Glide.with(context).load(imageUrl).into(binding.ivMovieThumb)
            }
    }
}

class MyDiffUtil: DiffUtil.ItemCallback<MoviesResult>() {
    override fun areItemsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviesResult, newItem: MoviesResult): Boolean {
        return oldItem == newItem
    }

}