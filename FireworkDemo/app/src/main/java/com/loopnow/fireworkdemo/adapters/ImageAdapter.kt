package com.loopnow.fireworkdemo.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageAdapter {
    @BindingAdapter("contentUrl")
    @JvmStatic
    fun setImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}