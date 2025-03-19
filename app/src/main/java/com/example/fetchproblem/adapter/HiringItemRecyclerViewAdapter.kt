package com.example.fetchproblem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchproblem.R
import com.example.fetchproblem.model.HiringItem

class HiringItemRecyclerViewAdapter(private val dataSet: List<HiringItem>) :
    RecyclerView.Adapter<HiringItemRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listIdTextView: TextView = view.findViewById(R.id.listId_textView)
        val idTextView: TextView = view.findViewById(R.id.id_textView)
        val nameTextView: TextView = view.findViewById(R.id.name_textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.hiring_item_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.listIdTextView.text = dataSet[position].listId.toString()
        viewHolder.idTextView.text = dataSet[position].id.toString()
        viewHolder.nameTextView.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size

}