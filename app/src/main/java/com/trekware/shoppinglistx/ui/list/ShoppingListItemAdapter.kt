package com.trekware.shoppinglistx.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trekware.shoppinglistx.R
import com.trekware.shoppinglistx.data.ShoppingListItem

class ShoppingListItemAdapter: RecyclerView.Adapter<ShoppingListItemAdapter.ViewHolder>() {
    var data = listOf<ShoppingListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ShoppingListItemAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ShoppingListItem) {
            val res = itemView.context.resources
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.shopping_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}