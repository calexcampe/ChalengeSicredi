package com.admin.chalengesicredi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.admin.chalengesicredi.adapter.viewholder.EventViewHolder
import com.admin.chalengesicredi.model.Event


class EventAdapter(private val cellClickListener: CellClickListene) : ListAdapter<
        Event,
        androidx.recyclerview.widget.RecyclerView.ViewHolder>
    (
         REPO_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return EventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)

        if (repoItem != null) {
            (holder as EventViewHolder).bind(repoItem,cellClickListener)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
                    oldItem.titulo == newItem.titulo

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
                    oldItem == newItem
        }
    }
}
