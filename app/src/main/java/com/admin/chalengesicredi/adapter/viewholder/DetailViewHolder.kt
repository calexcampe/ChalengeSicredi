package com.admin.chalengesicredi.adapter.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admin.chalengesicredi.R
import com.admin.chalengesicredi.model.DetailEvent
import java.text.SimpleDateFormat
import java.util.*

class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.textV_titledetail)
    private val description: TextView = view.findViewById(R.id.textV_descricaodetail)

    private val price: TextView = view.findViewById(R.id.textV_price)
    private var urlavatar: String? = null
    private val data: TextView = view.findViewById(R.id.textV_data)
    private var repo: DetailEvent? = null


    fun bind(repo: DetailEvent) {

        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            description.visibility = View.GONE
            data.visibility = View.GONE
            price.visibility = View.GONE
        } else {
            showRepoData(repo)

        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun showRepoData(repo: DetailEvent) {
        this.repo = repo
        name.text = repo.titulo.toString()

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (repo.titulo != null) {
            description.text = repo.descricao
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility


        price.text = repo.preco.toString()
        urlavatar = repo.imgevent

        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = repo.date * 1000
        data.text =formatter.format(calendar.getTime())
    }

    companion object {
        fun create(parent: ViewGroup): DetailViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.detailevents, parent, false)
            return DetailViewHolder(view)
        }
    }
}
