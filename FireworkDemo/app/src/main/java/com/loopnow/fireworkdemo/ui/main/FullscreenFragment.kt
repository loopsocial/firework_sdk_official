package com.loopnow.fireworkdemo.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworklibrary.FireworkSDK

class FullscreenFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_fullscreen, container, false)
    }




    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): FullscreenFragment {
            return FullscreenFragment()
        }
    }


}