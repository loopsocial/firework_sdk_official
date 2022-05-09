package com.loopnow.fireworkdemo.ui.main


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import com.loopnow.fireworkdemo.models.VideoFeed

open class IntegratedViewModel : ViewModel() {
    val intengratedContent = MutableLiveData<List<DemoContent>>()

    fun getContent()  : LiveData<List<DemoContent>> {
        if(intengratedContent.value == null ) {
            Thread {
                prepareContent()
            }.start()
        }
        return intengratedContent
    }

    @WorkerThread
    open fun prepareContent() {
        val contentFeed = ArrayList<DemoContent>()

        contentFeed.add(VideoFeed("test", R.layout.videofeed_item))

        intengratedContent.postValue(contentFeed)
    }

    val keywords = arrayOf(
            "Halloween",
            "Bollywood",
            "Bollywood Dance",
            "Dance India",
            "Horror",
            "Halloween Makeup",
            "Scary"
    )
}

