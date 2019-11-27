package com.loopnow.fireworkdemo.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworklibrary.FireworkSDK.Companion.debug
import kotlinx.android.synthetic.main.fragment_pinned.*

class PinnedFragment  : IntegratedFragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
      return  inflater.inflate(R.layout.fragment_pinned, container, false)

    }


    override fun setViewModel() {
            viewModel = ViewModelProviders.of(this).get(PinnedViewModel::class.java)
            viewModel.getContent().observe(this, Observer {
                it?.let { content ->
                    adapter.contentFeed.clear()
                    adapter.contentFeed.addAll(content)
                    adapter.notifyDataSetChanged()
                }
            })
    }


   override fun setRecyclerView(view: View ) {
        pinned_recycler_view.layoutManager = LinearLayoutManager(view.context)
        pinned_recycler_view.setHasFixedSize(true)
        pinned_recycler_view.adapter = adapter
    }
    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): PinnedFragment {
            return PinnedFragment()
        }
    }


}