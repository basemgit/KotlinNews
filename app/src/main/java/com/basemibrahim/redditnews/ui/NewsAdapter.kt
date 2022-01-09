package com.basemibrahim.redditnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.redditnews.data.model.api.Children
import com.basemibrahim.redditnews.databinding.GridViewItemBinding


class NewsAdapter : ListAdapter<Children,
        NewsAdapter.NewsViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.NewsViewHolder {
        return NewsViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class NewsViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(child: Children) {
            binding.item = child
            binding.root.setOnClickListener {
               val action = ListFragmentDirections.actionListFragmentToDetailsFragment(title =
               child.data.title,img = child.data.media?.oembed?.thumbnail_url.toString()
              , body = child.data.selftext )
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.data.id == newItem.data.id

        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.data.thumbnail == newItem.data.thumbnail
        }
    }
}