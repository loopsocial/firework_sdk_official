package com.loopnow.fireworkdemo.ui.main

import android.support.annotation.WorkerThread
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import com.loopnow.fireworkdemo.models.VideoFeed

class PinnedViewModel : IntegratedViewModel() {

    @WorkerThread
    override fun prepareContent() {
        val contentFeed = ArrayList<DemoContent>()


        for(i in 0 until Math.min(imageUrls.size,textContent.size) -1 ) {
            contentFeed.add( Content(imageUrls[i], textContent[i], R.layout.content_item))
        }
        intengratedContent.postValue(contentFeed)
    }
}