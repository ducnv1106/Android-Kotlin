package com.ducnv1106.sell.utils


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ducnv1106.sell.R

fun ImageView.showFitXY(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.show(url: String) {
    val requestOptions = RequestOptions()
        .error(R.drawable.image_car)
        .centerCrop()
    Glide.with(this)
        .load(url).apply(requestOptions)
        .override(400, 400)
        .into(this)
}
