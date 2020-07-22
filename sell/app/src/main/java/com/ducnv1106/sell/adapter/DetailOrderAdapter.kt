package com.ducnv1106.sell.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ducnv1106.sell.R
import com.ducnv1106.sell.model.Product
import com.ducnv1106.sell.utils.showFitXY
import com.ducnv1106.sell.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_order_detail.view.*
import ru.rambler.libs.swipe_layout.SwipeLayout


class DetailOrderAdapter(var context:Context) :  RecyclerView.Adapter<DetailOrderAdapter.DetailViewHolder>(){

    var data= mutableListOf<Product>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private lateinit var snackbar: Snackbar

    private val model by lazy { HomeViewModel() }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder = DetailViewHolder(LayoutInflater.from(context).inflate(
        R.layout.item_order_detail,parent,false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val item =data[position]

        with(holder.itemView){
            this.image.showFitXY(item.imageProduct)
            this.tv_name.text="Name: "+item.nameProduct
            this.tv_pirce.text=item.priceProduct
            this.layout_swipe.setOnSwipeListener(object :SwipeLayout.OnSwipeListener{
                override fun onRightStickyEdge(swipeLayout: SwipeLayout?, moveToRight: Boolean) {

                }

                override fun onBeginSwipe(swipeLayout: SwipeLayout?, moveToRight: Boolean) {

                }

                override fun onLeftStickyEdge(swipeLayout: SwipeLayout?, moveToRight: Boolean) {

                }

                override fun onSwipeClampReached(swipeLayout: SwipeLayout?, moveToRight: Boolean) {
                   if (!moveToRight) {
                       removeItem(holder.adapterPosition)
                       model.unOrder()
                        snackbar = Snackbar.make(this@with, "remove form item", 3000)
                       snackbar.setAction("UNDO", {
                           restoreItem(item, position)
                           holder.itemView.layout_swipe.reset()

                       })
                       snackbar.setActionTextColor(Color.YELLOW)
                       snackbar.show()
                   }

                }

            })
        }
    }

    class DetailViewHolder(view:View):RecyclerView.ViewHolder(view){

    }

    private fun removeItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun restoreItem(item:Product,position: Int){
        data.add(position,item)
        notifyItemInserted(position)
        model.plusOrder()

    }
}