package com.android.sapo.ui.city.adapter

import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.sapo.R
import com.android.sapo.data.model.City
import com.core.BaseViewHolder
import com.core.OnItemClick
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter : RecyclerView.Adapter<BaseViewHolder>() {

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
        Handler().postDelayed({
            notifyDataSetChanged()
        }, 300)
        return items[pos]
    }

    var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseViewHolder = BaseViewHolder(viewGroup
            .inflateExt(R.layout.item_city))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, pos: Int) {
        val item = items[pos]

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