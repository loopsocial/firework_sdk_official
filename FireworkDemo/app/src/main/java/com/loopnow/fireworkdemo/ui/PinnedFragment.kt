package com.loopnow.fireworkdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.ui.IntegratedFragment

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
       val rc = view.findViewById<RecyclerView>(R.id.pinned_recycler_view)
       rc.layoutManager = LinearLayoutManager(view.context)
       rc.setHasFixedSize(true)
       rc.adapter = adapter
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