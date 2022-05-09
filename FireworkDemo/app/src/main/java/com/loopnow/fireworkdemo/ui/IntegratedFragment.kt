package com.loopnow.fireworkdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.adapters.IntegratedAdapter
import com.loopnow.fireworkdemo.ui.main.IntegratedViewModel

/**
 * A placeholder fragment containing a simple view.
 */
open class IntegratedFragment : Fragment() {

    lateinit var viewModel: IntegratedViewModel

    val adapter: IntegratedAdapter by lazy {
        IntegratedAdapter(activity!!, activity!!.supportFragmentManager)
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
        val rc = view.findViewById<RecyclerView>(R.id.recycler_view)
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
        fun newInstance(): IntegratedFragment {
            return IntegratedFragment()
        }
    }
}