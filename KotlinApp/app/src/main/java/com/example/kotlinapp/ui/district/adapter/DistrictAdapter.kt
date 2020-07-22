package com.example.kotlinapp.ui.district.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.BaseViewHolder
import com.core.OnItemClick
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.District
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_city.view.*
import java.util.logging.Handler

class DistrictAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var items = mutableListOf<District>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: OnItemClick? = null

    fun getDistrict(position: Int): District {

        val item = items[position]
        items.map {
            it.selected = it.districtCode == item.districtCode
        }

        android.os.Handler().postDelayed({notifyDataSetChanged()},300)

        return items[position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent.inflateExt(R.layout.item_city))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val item = items[position]

        with(holder.itemView) {
            tvNameCity.text = item.name
            if (item.selected) {
                tvNameCity.setBackgroundResource(R.drawable.bg_selected)
            } else {
                tvNameCity.setBackgroundResource(R.drawable.bg_default)
            }

            setOnClickListener { onItemClick?.onItemClickListener(it, holder.adapterPosition) }
        }
    }
}