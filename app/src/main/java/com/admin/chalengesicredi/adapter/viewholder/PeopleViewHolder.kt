package com.admin.chalengesicredi.adapter.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.model.DetailEvent
import com.admin.chalengesicredi.model.People
import com.squareup.picasso.Picasso

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.titlepeople)
    private var urlavatar: String? = null
    private val img: ImageView = view.findViewById(R.id.imgpeople)

    private var repo: People? = null


    fun bind(repo: People) {

        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
        } else {
            showRepoData(repo)

        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun showRepoData(repo: People) {
        this.repo = repo
        name.text = repo.peoplename

        urlavatar = repo.imgpeople
        Picasso.get().load(urlavatar).into(img)

    }

    companion object {
        fun create(parent: ViewGroup): PeopleViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.people, parent, false)
            return PeopleViewHolder(view)
        }
    }
}
