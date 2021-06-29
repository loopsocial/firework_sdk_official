package com.loopnow.fireworkdemo

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class SettingViewModel(app: Application)  : AndroidViewModel(app) {

    val appIdLive = MutableLiveData<Boolean>()
    val bundleIdLive = MutableLiveData<Boolean>()
    var appId: String? = null
    var bundleId: String? = null


    val enableSaveButton = MediatorLiveData<Boolean>().apply {
        var bundleIdOk = false
        var appIdOk = false

        fun enableSave() {
            value  = bundleIdOk && appIdOk
        }

        addSource(appIdLive) {
            appIdOk = it
            enableSave()
        }
        addSource(bundleIdLive) {
            bundleIdOk = it
            enableSave()
        }
    }

    val bundleIdTextWatcher  = object: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            bundleIdLive.value = (p0?.length?: 0)  > 0
            bundleId = p0?.toString()

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }

    val appIdTextWatcher = object: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            appIdLive.value = (p0?.length?: 0)  > 0
            appId = p0?.toString()

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("bundleIdTextWatcher")
        fun setBundleIdTextWatcher(bundleIdEditText: EditText, listener: TextWatcher) {
            bundleIdEditText.addTextChangedListener(listener)
        }


        @JvmStatic
        @BindingAdapter("appIdTextWatcher")
        fun setAppIdTextWatcher(appIdEditText: EditText, listener: TextWatcher) {
            appIdEditText.addTextChangedListener(listener)
        }

        /*@JvmStatic
        @BindingAdapter("enabled")
        fun setEnabled(saveButton: TextView, enabled: Boolean) {
            saveButton.isEnabled = enabled
        }*/
    }
}
