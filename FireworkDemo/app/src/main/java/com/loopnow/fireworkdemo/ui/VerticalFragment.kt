package com.loopnow.fireworkdemo.ui.main



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.ui.IntegratedFragment

class VerticalFragment  : IntegratedFragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_vertical, container, false)
    }


    override fun setViewModel() {

    }


    override fun setRecyclerView(view: View ) {
    }

    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): VerticalFragment {
            return VerticalFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}