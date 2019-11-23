package com.loopnow.testlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loopnow.mylibrary.FireworkSDK

class MainActivity : AppCompatActivity() {
    lateinit var mylib: FireworkSDK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mylib = FireworkSDK.initialize()
    }
}
