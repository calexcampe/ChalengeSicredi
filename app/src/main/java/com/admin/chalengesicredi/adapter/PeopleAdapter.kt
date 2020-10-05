package com.admin.chalengesicredi.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.admin.chalengesicredi.adapter.viewholder.EventViewHolder
import com.admin.chalengesicredi.adapter.viewholder.PeopleViewHolder
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.People


class PeopleAdapter: ListAdapter<
        People,
        androidx.recyclerview.widget.RecyclerView.ViewHolder>
    (
         REPO_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return PeopleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)

        if (repoItem != null) {
            (holder as PeopleViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
                    oldItem.idpeople == newItem.idpeople

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
                    oldItem == newItem
        }
    }
}
