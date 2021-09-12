package com.trekware.shoppinglistx.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trekware.shoppinglistx.R
import com.trekware.shoppinglistx.data.ShoppingListItem
import com.trekware.shoppinglistx.databinding.FragmentListBinding
import org.w3c.dom.Text

class ListItemAdapter(): RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {
    var data = listOf<ShoppingListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface ShoppingListItemAdapterListener {
        fun onShoppingListItemClick(listView : View, listItem: ShoppingListItem)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ListItemAdapter.ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox : CheckBox = itemView.findViewById(R.id.checkbox)
        val description : TextView = itemView.findViewById(R.id.description)
        val quantity : TextView = itemView.findViewById(R.id.qty)
        val cost : TextView = itemView.findViewById(R.id.cost)

        fun bind(item: ShoppingListItem) {
            val res = itemView.context.resources
            checkbox.isChecked = item.isItemFill
            description.text = item.description
            quantity.text = item.itemQty.toString()
            val value = res.getText(R.string.cost).toString() +  item.itemCost.toString()
            cost.text = value
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