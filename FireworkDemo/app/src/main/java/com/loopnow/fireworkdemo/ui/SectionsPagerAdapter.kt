package com.loopnow.fireworkdemo.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.ui.IntegratedFragment
import com.loopnow.fireworklibrary.Key
import com.loopnow.fireworklibrary.views.FireworkPlayerFragment

private val TAB_TITLES = arrayOf(
        R.string.integrated,
        R.string.pinned,
        R.string.grid,
        R.string.vertical,
        R.string.player
        //R.string.view
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, val fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    var current = 0
    var lastPage = 0

    val fragmentMap = HashMap<Int, Fragment>()


    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return when (position) {

            1 -> {
                if (!fragmentMap.containsKey(position)) {
                    fragmentMap[position] = PinnedFragment.newInstance()
                }
                fragmentMap[position]!!
            }

            2 -> {
                if (!fragmentMap.containsKey(position)) {
                    fragmentMap[position] = GridFragment.newInstance()
                }
                fragmentMap[position]!!
            }
            3 -> {
                if (!fragmentMap.containsKey(position)) {
                    fragmentMap[position] = VerticalFragment.newInstance()
                }
                fragmentMap[position]!!
            }

            4 -> {
                if (!fragmentMap.containsKey(position)) {
                    val fragment = FireworkPlayerFragment()
                    //fragment.autoPlay = false

                    val bundle = Bundle()
                    bundle.putString(Key.APP_ID, "BQEaIb9E80fiqAyaNQ5uv5_II5lXL3ET")

                    fragment.arguments = bundle

                    fragmentMap[position] = fragment
                }
                fragmentMap[position]!!
            }
            else -> {
                if (!fragmentMap.containsKey(position)) {
                    val fragment = IntegratedFragment.newInstance()
                    fragmentMap[position] = fragment
                }
                fragmentMap[position]!!
            }

        }

    }


    fun setCurrentPage(page: Int) {
        if(current == 4 ) {
            (fragmentMap[4] as FireworkPlayerFragment).pausePlayback()
        }

        /*if(current == 5 ) {
            (fragmentMap[5] as VideoViewFragment).pausePlayback()
        }*/

        lastPage = current
        current = page

        if(current == 4 ) {
            (fragmentMap[4] as FireworkPlayerFragment).startPlayback()
        }

        /*if(current == 5 ) {
            (fragmentMap[5] as VideoViewFragment).resumePlayback()
        }*/


    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        //return 2
        return TAB_TITLES.size
        //return  1
    }
}