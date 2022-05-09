package com.loopnow.fireworkdemo.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.databinding.ContentItemBinding
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import com.loopnow.fireworklibrary.views.VideoFeedView

class IntegratedAdapter(val activity: Activity, val supportFragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val contentFeed = ArrayList<DemoContent>()


    override fun onCreateViewHolder(parent: ViewGroup, layout: Int): RecyclerView.ViewHolder {
        return when(layout) {
            R.layout.content_item -> {
                ContentItemViewHolder(ContentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }

            R.layout.videofeed_item -> {
                VideofeedItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.videofeed_item,parent,false))
            }

            else -> {
                ContentItemViewHolder(ContentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }

        }
    }

    override fun getItemCount(): Int = contentFeed.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when(p0) {
            is ContentItemViewHolder -> {
                p0.binding.content = ((contentFeed[p1]) as Content).text
                p0.binding.imageUrl = ((contentFeed[p1]) as Content).url
            }
            is VideofeedItemViewHolder -> {
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return contentFeed[position].layout
    }

    internal class ContentItemViewHolder(val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root)
    internal class VideofeedItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val videoFeedView = view.findViewById<VideoFeedView>(R.id.integrated_videofeed)
    }
}