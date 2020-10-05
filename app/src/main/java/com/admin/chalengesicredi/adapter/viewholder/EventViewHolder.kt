package com.admin.chalengesicredi.adapter.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.adapter.CellClickListene
import com.admin.chalengesicredi.model.Event
import com.squareup.picasso.Picasso

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.titleevent)
    private val description: TextView = view.findViewById(R.id.descr_event)
    private var urlavatar: String? = null
    private val imageView: ImageView = view.findViewById(R.id.imgevent)

    private var repo: Event? = null

    init {
        getclick()
    }

    fun getclick(){

        itemView.setOnClickListener {

        }
    }


    fun bind(repo: Event, cellClickListener: CellClickListene) {

        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
            imageView.visibility = View.GONE
        } else {
            showRepoData(repo, cellClickListener)

        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun showRepoData(repo: Event, cellClickListener: CellClickListene) {
        this.repo = repo
        name.text = repo.titulo.toString()

        var descriptionVisibility = View.GONE
        if (repo.titulo != null) {
            description.text = repo.descricao
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility
        urlavatar = repo.imgevent
        Picasso.get().load(urlavatar).into(imageView)

        itemView.setOnClickListener {

            cellClickListener.onCellClickListener(repo)
        }
    }

    companion object {
        fun create(parent: ViewGroup): EventViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.events, parent, false)
            return EventViewHolder(view)
        }
    }
}
