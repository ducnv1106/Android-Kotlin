package com.ducnv1106.sell

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.ducnv1106.sell.utils.showFitXY

class AppBinding {
    companion object{
        @BindingAdapter("android:showImage")
        @JvmStatic
        fun showImage(image:AppCompatImageView,@DrawableRes intImage:Int){
            image.setImageResource(intImage)
        }
        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(image:AppCompatImageView,urlImage:String){
            image.showFitXY(urlImage)
        }

    }
}