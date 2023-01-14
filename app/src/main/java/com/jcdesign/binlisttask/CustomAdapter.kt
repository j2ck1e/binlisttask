package com.jcdesign.binlisttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var mList: MutableList<RecyclerItem>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container: ConstraintLayout = itemView.findViewById(R.id.item_recycler_container)
        val scheme: TextView = itemView.findViewById(R.id.item_scheme_textview)
        val brand: TextView = itemView.findViewById(R.id.item_brand_textview)
        val length: TextView = itemView.findViewById(R.id.item_length_textview)
        val type: TextView = itemView.findViewById(R.id.item_type_textview)
        val prepaid: TextView = itemView.findViewById(R.id.item_prepaid_textview)
        val country: TextView = itemView.findViewById(R.id.item_country_textview)
        val name: TextView = itemView.findViewById(R.id.item_name_textview)
        val site: TextView = itemView.findViewById(R.id.item_scheme_textview)
        val tel: TextView = itemView.findViewById(R.id.item_tel_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.scheme.text = mList[position].scheme
        holder.brand.text = mList[position].brand
        holder.length.text = mList[position].length.toString()
        holder.type.text = mList[position].type
        holder.prepaid.text = mList[position].prepaid.toString()
        holder.country.text = mList[position].name
        holder.site.text = mList[position].site
        holder.tel.text = mList[position].tel

    }

    fun updateList(updatedList: List<RecyclerItem>){
        mList = updatedList.toMutableList()
        notifyDataSetChanged() // refresh recyclerView
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}