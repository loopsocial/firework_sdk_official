package com.loopnow.fireworkdemo.ui.main


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.adapters.IntegratedAdapter
import com.loopnow.fireworklibrary.FireworkSDK.Companion.debug
import kotlinx.android.synthetic.main.fragment_integrated.*

/**
 * A placeholder fragment containing a simple view.
 */
open class IntegratedFragment : Fragment() {

    lateinit var viewModel: IntegratedViewModel

    val adapter: IntegratedAdapter by lazy {
        IntegratedAdapter(activity!!.supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = false

        setViewModel()
    }

    open fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(IntegratedViewModel::class.java)
        viewModel.getContent().observe(this, Observer {
            it?.let { content ->
                adapter.contentFeed.clear()
                adapter.contentFeed.addAll(content)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_integrated, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(view)

    }

    open fun setRecyclerView(view: View) {
        recycler_view.layoutManager = LinearLayoutManager(view.context)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    companion object {


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): IntegratedFragment {
            return IntegratedFragment()
        }
    }
}