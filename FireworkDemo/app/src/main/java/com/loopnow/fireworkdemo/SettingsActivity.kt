package com.loopnow.fireworkdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.loopnow.fireworkdemo.databinding.FragmentSettingBinding

class SettingsActivity : AppCompatActivity() {


    var binding: FragmentSettingBinding? = null

    val viewModel by lazy {
         ViewModelProviders.of(this@SettingsActivity).get(SettingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = DataBindingUtil.setContentView<FragmentSettingBinding>(this,R.layout.fragment_setting,null).also {
                it.viewModel = viewModel
                it.enableSave = viewModel.enableSaveButton
                it.eventHandler = this
                it.lifecycleOwner = this
            }

    }

    fun saveButtonClicked(view: View , bundleId: String, appId: String)   {
        val intent = Intent()
        Log.v("TextLog"," bundleId : $bundleId   appId: $appId")
        intent.putExtra("bundle_id", bundleId)
        intent.putExtra("app_id",appId)

        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}


