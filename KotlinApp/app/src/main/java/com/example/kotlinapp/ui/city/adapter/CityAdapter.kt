package com.example.kotlinapp.ui.city.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.BaseViewHolder
import com.core.OnItemClick
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.City
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var onItemClick: OnItemClick? = null
    var items = mutableListOf<City>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun getCity(pos: Int): City {
        val item = items[pos]
        items.map {
            it.selected = it.code == item.code

        }
        android.os.Handler().postDelayed({
            notifyDataSetChanged()
        }, 300)
        return items[pos]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(
            parent.inflateExt(
                R.layout.item_city
            )
        )


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        with(holder.itemView) {
            tvNameCity.text = item.name
            if (item.selected) {
                tvNameCity.setBackgroundResource(R.drawable.bg_selected)
            }else{
                tvNameCity.setBackgroundResource(R.drawable.bg_default)
            }

            setOnClickListener {onItemClick?.onItemClickListener(it,holder.adapterPosition) }
        }
    }
}