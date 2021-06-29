package com.loopnow.fireworkdemo.adapters

import android.app.Activity
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.databinding.ContentItemBinding
import com.loopnow.fireworkdemo.databinding.VideofeedItem1Binding
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import com.loopnow.fireworklibrary.FeedType
import com.loopnow.fireworklibrary.FwSDK
import com.loopnow.fireworklibrary.views.OnItemClickedListener
//import com.loopnow.fireworklibrary.views.OnItemClickedListener
import com.loopnow.fireworklibrary.views.VideoFeedView
import kotlinx.android.synthetic.main.videofeed_item.view.*

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

                /*p0.videoFeedView.addOnItemClickedListener(object: OnItemClickedListener {
                    override fun onItemClicked(
                        index: Int,
                        feedId: Int
                    ): Boolean {
                        Handler().postDelayed ({
                            FwSDK.play(activity, index, feedId)
                        },1000)
                        return true
                    }

                })*/
                /*if(p0.videoFeedView.feedId == 420 ) {
                    p0.videoFeedView.setChannel("M2a1nE",FeedType.CHANNEL)
                }*/
               /* if(p0.videoFeedView.feedId == 430 ) {

                    p0.videoFeedView.setChannel("EKwaGk",FeedType.CHANNEL)
                    p0.videoFeedView.setPlaylist("LRY1GX","5zJPN5",FeedType.PLAYLIST)
                }*/

                //p0.videoFeedView.loadContent("")
                //p0.videoFeedView.loadContent("Food")
                /*p0.videoFeedView.addOnItemClickedListener(object: OnItemClickedListener {
                    override fun onItemClicked(
                        index: Int,
                        title: String,
                        id: String,
                        videoDuration: Float
                    ) {
                        Log.v("EventLog", " $index $title $id $videoDuration")
                    }
                })*/
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
    internal class VideofeedItemViewHolder1(val binding: VideofeedItem1Binding) : RecyclerView.ViewHolder(binding.root)







}