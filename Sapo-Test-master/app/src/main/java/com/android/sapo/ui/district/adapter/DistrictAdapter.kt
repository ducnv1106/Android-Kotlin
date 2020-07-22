package com.android.sapo.ui.district.adapter

import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.sapo.R
import com.android.sapo.data.model.District
import com.core.BaseViewHolder
import com.core.OnItemClick
import com.utils.ext.inflateExt
import kotlinx.android.synthetic.main.item_city.view.*

class DistrictAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var items = mutableListOf<District>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: OnItemClick? = null

    fun getDistrict(pos: Int): District {
        val item = items[pos]
        items.map {
            it.selected = it.districtCode == item.districtCode
        }
        Handler().postDelayed({
            notifyDataSetChanged()
        }, 300)
        return items[pos]
    }

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