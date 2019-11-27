package com.loopnow.fireworkdemo.adapters

import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.databinding.ContentItemBinding
import com.loopnow.fireworkdemo.databinding.VideofeedItemBinding
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import kotlinx.android.synthetic.main.videofeed_item.view.*

class IntegratedAdapter(val supportFragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val contentFeed = ArrayList<DemoContent>()


    override fun onCreateViewHolder(parent: ViewGroup, layout: Int): RecyclerView.ViewHolder {
        return when(layout) {
            R.layout.content_item -> {
                ContentItemViewHolder(ContentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }

            R.layout.videofeed_item -> {
                val binding = VideofeedItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                binding.parentLayout.id = View.generateViewId()

                val fragment = supportFragmentManager.findFragmentById(R.id.integrated_videofeed)

                if(fragment != null) {
                    val ft = supportFragmentManager.beginTransaction()
                    ft.remove(fragment)
                    ft.commitNow()

                }

                val fragmentView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_videofeed_item,binding.parentLayout,true)




                VideofeedItemViewHolder(binding)

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
    internal class VideofeedItemViewHolder(val binding: VideofeedItemBinding) : RecyclerView.ViewHolder(binding.root)






}