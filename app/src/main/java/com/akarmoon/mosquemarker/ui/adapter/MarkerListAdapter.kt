package com.akarmoon.mosquemarker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akarmoon.mosquemarker.databinding.ListItemMarkerBinding
import com.akarmoon.mosquemarker.model.Marker

class MarkerListAdapter(
    private val clickListener: (Marker) -> Unit
) : ListAdapter<Marker, MarkerListAdapter.MarkerViewHolder>(DiffCallback) {
    class MarkerViewHolder(
        private var binding: ListItemMarkerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marker: Marker) {
            binding.marker = marker
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Marker>() {
        override fun areItemsTheSame(oldItem: Marker, newItem: Marker): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Marker, newItem: Marker): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarkerViewHolder(
            ListItemMarkerBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        val marker = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(marker)
        }
        holder.bind(marker)
    }
}