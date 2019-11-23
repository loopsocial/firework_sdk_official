package com.loopnow.mylibrary

import android.util.Log
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


        @JvmStatic
        fun libDebug(s: String) {
            Log.v("SdkLog ", s)
        }

        @JvmStatic
        fun initialize(): FireworkSDK {
            synchronized(this) {
                if (::singleton.isInitialized) {
                    return singleton
                } else {
                    if(!::singleton.isInitialized) {
                        singleton = FireworkSDK()
                    }
                }
            }
            return singleton
        }
    }


}