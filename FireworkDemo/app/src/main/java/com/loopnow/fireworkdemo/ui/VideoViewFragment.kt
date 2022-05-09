package com.loopnow.fireworkdemo.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.adapters.PlaybackViewAdapter
import com.loopnow.fireworklibrary.*

class VideoViewFragment  : Fragment() {
    lateinit var fireworkSDK: FwSDK

   // val appid = "TIjq0YITcyqaz_zicjWpkx95gz_HAkzl"
    val appid = "qqDNva8NF8b0cM76prmEpHsm_PRko9WM"
    val bundle_id = "com.app.itap"

    private var viewPager: ViewPager? = null


    var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        rootView =   inflater.inflate(R.layout.fragment_videoview, container, false)
        rootView?.apply {
            viewPager = findViewById(R.id.videoview_viewpager)
        }

        return rootView
    }


    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): VideoViewFragment {
            return VideoViewFragment()
        }
    }

    val adapter by lazy {
        PlaybackViewAdapter(context!!, fireworkSDK)
    }

    private fun setUI() {
        viewPager?.adapter = adapter
        viewPager?.offscreenPageLimit = 2

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                adapter.currentVideo = p0
            }

        })
    }

     fun pausePlayback() {
     }

    fun resumePlayback() {

    }

    private fun getFeed() {

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}