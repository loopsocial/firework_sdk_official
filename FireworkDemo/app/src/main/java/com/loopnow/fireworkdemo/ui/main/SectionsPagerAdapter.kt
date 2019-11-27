package com.loopnow.fireworkdemo.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworklibrary.views.FireworkPlayerFragment

private val TAB_TITLES = arrayOf(
        R.string.integrated,
        R.string.pinned,
        R.string.grid,
        R.string.vertical
        //R.string.player
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, val fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return when(position) {

            0 -> {
                var fragment = fm.findFragmentById(R.id.integrated_videofeed)
                if(fragment == null) {
                    fragment = IntegratedFragment.newInstance()
                }
                fragment

            }
            1 -> {
                var fragment = fm.findFragmentById(R.id.pinned_videofeed)
                if(fragment == null) {
                    fragment = PinnedFragment.newInstance()
                }
                fragment
            }
            2 -> {
                var fragment = fm.findFragmentById(R.id.grid_videofeed)
                if(fragment == null) {
                    fragment = GridFragment.newInstance()
                }
                fragment
            }
            3 -> {
                var fragment = fm.findFragmentById(R.id.vertical_videofeed)
                if(fragment == null) {
                    fragment = VerticalFragment.newInstance()
                }
                fragment
            }

            else  -> {
                var fragment = fm.findFragmentById(R.id.firework_player)
                if(fragment == null) {
                        fragment = FullscreenFragment.newInstance()

                }
                fragment
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}