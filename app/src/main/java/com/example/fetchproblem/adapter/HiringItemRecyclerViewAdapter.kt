package com.example.fetchproblem.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproblem.R
import com.example.fetchproblem.model.ListItem

class HiringItemRecyclerViewAdapter(private val dataSet: List<ListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.id_textView)
        val nameTextView: TextView = view.findViewById(R.id.name_textView)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hiringItemHeaderTextView: TextView = view.findViewById(R.id.hiringItemHeaderTextView)
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].type
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.hiring_item_header_layout, viewGroup, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.hiring_item_layout, viewGroup, false)
            ItemViewHolder(view)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is HeaderViewHolder) {
            viewHolder.hiringItemHeaderTextView.text = "listId: ${dataSet[position].header}"
        } else if (viewHolder is ItemViewHolder) {
            viewHolder.idTextView.text = "id: ${dataSet[position].item?.id.toString()}"
            viewHolder.nameTextView.text = "name: ${dataSet[position].item?.name}"
        }
    }

    override fun getItemCount() = dataSet.size

}