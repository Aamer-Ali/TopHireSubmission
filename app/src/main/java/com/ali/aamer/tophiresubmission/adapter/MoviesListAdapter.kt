package com.ali.aamer.tophiresubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ali.aamer.tophiresubmission.databinding.MoviesListItemBinding
import com.ali.aamer.tophiresubmission.models.MoviesList.Result

class MoviesListAdapter() :
    ListAdapter<Result, MoviesListAdapter.MoviesListViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val binding =
            MoviesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    inner class MoviesListViewHolder(private val binding: MoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: com.ali.aamer.tophiresubmission.models.MoviesList.Result) {
            binding.title.text = movie.title
            binding.desc.text = movie.overview
        }
    }

    class ComparatorDiffUtil :
        DiffUtil.ItemCallback<com.ali.aamer.tophiresubmission.models.MoviesList.Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }


}