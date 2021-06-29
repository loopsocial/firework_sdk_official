package com.loopnow.fireworkdemo

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.style.ClickableSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout
import com.loopnow.fireworkdemo.ui.main.SectionsPagerAdapter
import com.loopnow.fireworklibrary.FeedType
import com.loopnow.fireworklibrary.FwSDK
import com.loopnow.fireworklibrary.SdkStatus
import com.loopnow.fireworklibrary.models.AdType
import com.loopnow.fireworklibrary.utils.UserLoginStatus
import org.json.JSONObject


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // if activity is launched due to user clicking on the deeplink
        // then refer to the code below for how to handle it and launch
        // the player.

        val uri: Uri? = intent.data

        uri?.let {
            val openDeepLink = uri.scheme ?: "" in listOf(
                "http",
                "https"
            ) && uri.host == "yourbasedomain.com"

            // Until SDK is initialized
            MainApplication.fwInitialized.observe(this, {
                if (it && openDeepLink) {
                    val parts = uri.toString().split("fwplayer=")
                    if (parts.size > 1) {
                        FwSDK.play(parts[1])
                    }
                }
            })
        }

        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 5
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(p0: Int) {
                sectionsPagerAdapter.setCurrentPage(p0)
            }
        })*/

        FwSDK.addVideoEventListener(object : FwSDK.VideoEventListener {
            override fun event(event: String, jsonObject: JSONObject) {
                // placyback events and json payload
                // refer to documentation for event details and payload
                // https://docs.firework.tv/android-sdks/android
                // tab playback events
            }
        })

        // This is how you would customize the share url which when shared
        // can be handled as deeplink
        FwSDK.setBasePlayerUrl("https://yourbasedomain.com")

        // If you were to handle the CTA click,
        // Implement CtaClickHandler

        FwSDK.ctaClickHandler = object : FwSDK.CtaClickHandler {
            /**
             * return true if you handled, false if you want SDK to handle it.
             */
            override fun ctaClicked(label: String, actionUrl: String?): Boolean {
                return false
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }




}

