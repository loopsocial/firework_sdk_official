package com.loopnow.mylibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread


class FireworkSDK constructor(){


    /**
     *
     * Initializes firework SDK
     *
     */
    @MainThread
    companion object {


        lateinit var singleton: FireworkSDK
        var  tokenReceived = false

        @JvmStatic
        fun debug(s: String) {
            Log.v("SdkLog ", s)
        }

        @JvmStatic
        fun initialized() : Boolean {
            return ::singleton.isInitialized
        }

        @JvmStatic
        fun initialize(): FireworkSDK {
            Log.d("test", "test ----")
            return singleton
        }
    }


}