package com.example.myapplication.exercise.db.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.item_movie.view.*

class ListMovieAdapter(private val listMovie: List<String>): RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun dataBindViewHolder(nameMovie: String) {
            itemView.itemMovie.text = nameMovie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBindViewHolder(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}