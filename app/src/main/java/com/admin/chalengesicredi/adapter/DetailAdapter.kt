package com.admin.chalengesicredi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.admin.chalengesicredi.adapter.viewholder.DetailViewHolder
import com.admin.chalengesicredi.adapter.viewholder.EventViewHolder
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.Event


class DetailAdapter : ListAdapter<
        DetailEvent,
        androidx.recyclerview.widget.RecyclerView.ViewHolder>
    (
         REPO_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return DetailViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)

        if (repoItem != null) {
            (holder as DetailViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<DetailEvent>() {
            override fun areItemsTheSame(oldItem: DetailEvent, newItem: DetailEvent): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DetailEvent, newItem: DetailEvent): Boolean =
                    oldItem == newItem
        }
    }
}
