package com.hanzama.battlesofww1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanzama.battlesofww1.data.model.dataArtikel
import com.hanzama.battlesofww1.R

class artikelAdapter(private val onClick: (dataArtikel) -> Unit) : ListAdapter<dataArtikel, artikelAdapter.ArtikelViewHolder>(ArtikelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.artikelcard, parent, false)
        return ArtikelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class ArtikelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val overviewTextView: TextView = itemView.findViewById(R.id.overviewTextView)
        private val articleImageView: ImageView = itemView.findViewById(R.id.articleImageView)

        fun bind(artikel: dataArtikel, onClick: (dataArtikel) -> Unit) {
            titleTextView.text = artikel.title
            overviewTextView.text = artikel.overview
            Glide.with(itemView.context).load(artikel.imageUrl).into(articleImageView)

            itemView.setOnClickListener {
                onClick(artikel)
            }
        }
    }
}

class ArtikelDiffCallback : DiffUtil.ItemCallback<dataArtikel>() {
    override fun areItemsTheSame(oldItem: dataArtikel, newItem: dataArtikel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: dataArtikel, newItem: dataArtikel): Boolean {
        return oldItem == newItem
    }
}
