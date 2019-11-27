package com.loopnow.fireworkdemo.adapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageAdapter {
    @BindingAdapter("contentUrl")
    @JvmStatic
    fun setImage(view: ImageView, imageUrl: String) {

            Picasso.with(view.context).load(imageUrl).into(view)

    }
}