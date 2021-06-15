package com.loopnow.fireworkdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.loopnow.fireworkdemo.ui.main.SectionsPagerAdapter
import com.loopnow.fireworklibrary.FireworkSDK

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 4
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                sectionsPagerAdapter.setCurrentPage(p0)
            }
        })

        FireworkSDK.addVideoPlaybackTracker(object: FireworkSDK.VideoPlaybackTracker{
            override fun nowPlaying(title: String, id: String, duration: Long) {

            }

            override fun videoWatched(
                title: String,
                id: String,
                totalDuration: Long,
                badge: String?
            ) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}