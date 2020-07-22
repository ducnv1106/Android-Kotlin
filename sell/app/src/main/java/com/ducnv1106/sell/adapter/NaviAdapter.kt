package com.ducnv1106.sell.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ducnv1106.sell.R
import com.ducnv1106.sell.BR
import com.ducnv1106.sell.model.ProductType

class NaviAdapter(private val context: Context) :
    RecyclerView.Adapter<NaviAdapter.NaviViewHolder>() {

    companion object {
        const val ITEM_NAVI_HEADER = 0
        const val ITEM_NAVI_BODY = 1
    }

    var listener:NaviAdaperListener?=null

    var data = mutableListOf<ProductType>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaviViewHolder {
        val binding: ViewDataBinding = if (viewType == ITEM_NAVI_HEADER) DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_header_navigation, parent, false
        )
        else DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_body_navigation,
            parent,
            false
        )
        return NaviViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: NaviViewHolder, position: Int) {
        val item=data[position]
        holder.binding.setVariable(BR.item,item)
        holder.binding.setVariable(BR.listener,listener)
        holder.binding.setVariable(BR.position,holder.adapterPosition)
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return if (item.typeHeader) ITEM_NAVI_HEADER
        else ITEM_NAVI_BODY
    }

    class NaviViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface NaviAdaperListener{
        fun onItemClicked(position: Int)
    }
}