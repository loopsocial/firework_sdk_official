package com.loopnow.fireworkdemo.adapters

import android.content.Context
import android.graphics.drawable.ColorDrawable

import android.view.View

import java.util.*
import kotlin.collections.ArrayList
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.viewpager.widget.PagerAdapter
import com.loopnow.fireworkdemo.R

import com.loopnow.fireworklibrary.FwSDK
import com.loopnow.fireworklibrary.views.VideoView

class PlaybackViewAdapter(val context: Context, val sdk: FwSDK) : PagerAdapter() {

    var videoList = ArrayList<String>()
    var currentVideo = -1
    // Whatever value is set in view_pager add 1 to it pointing to next index

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    fun setData(videos: List<String>) {
        this.videoList.addAll(videos)

    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val video = videoList[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.fw_playbackview_item, collection, false) as ViewGroup
        layout.background = ColorDrawable(getRandomColor())

        val videoView = if(layout.childCount > 0) layout.getChildAt(0) as VideoView else null

        videoView?.apply {
            //@todo
           // setVideo(position,video, embedInstance)
        } ?: run {
        }
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int = videoList.size

    private fun getRandomColor(): Int {
        val random = Random()
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        var color = 255 shl 24
        color = color or (r shl 16)
        color = color or (g shl 8)
        color = color or b

        return color
    }

}
