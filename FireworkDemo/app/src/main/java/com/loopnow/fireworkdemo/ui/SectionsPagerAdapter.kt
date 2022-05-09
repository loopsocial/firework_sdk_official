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
        R.string.grid,
        R.string.vertical,
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class SectionsPagerAdapter(private val context: Context, private val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var current = 0
    var lastPage = 0

    private val fragmentMap = HashMap<Int, Fragment>()


    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return when (position) {
            1 -> {
                if (!fragmentMap.containsKey(position)) {
                    fragmentMap[position] = GridFragment.newInstance()
                }
                fragmentMap[position]!!
            }
            2 -> {
                if (!fragmentMap.containsKey(position)) {
                    fragmentMap[position] = VerticalFragment.newInstance()
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

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        //return 2
        return TAB_TITLES.size
        //return  1
    }
}