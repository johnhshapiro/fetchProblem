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

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listIdTextView: TextView
        val idTextView: TextView
        val nameTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            listIdTextView = view.findViewById(R.id.listId_textView)
            idTextView = view.findViewById(R.id.id_textView)
            nameTextView = view.findViewById(R.id.name_textView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.hiring_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.listIdTextView.text = dataSet[position].listId.toString()
        viewHolder.idTextView.text = dataSet[position].id.toString()
        viewHolder.nameTextView.text = dataSet[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}