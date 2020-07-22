package com.ducnv1106.sell.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ducnv1106.sell.BR
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.rambler.libs.swipe_layout.SwipeLayout

class BaseAdapter<T >(private val context: Context, private val layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    var data = mutableListOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: BaseListener? = null
    var image: String? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            layoutId,
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = data[position]
        holder.binding.setVariable(BR.item, item)
        holder.binding.setVariable(BR.position, holder.adapterPosition)
        holder.binding.setVariable(BR.listener, listener)
        holder.binding.setVariable(BR.image, image)
        holder.binding.executePendingBindings()

    }


    class BaseViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface BaseListener {
        fun onItemClicked(position: Int)
        fun onOrderClicked(position: Int)
    }



}