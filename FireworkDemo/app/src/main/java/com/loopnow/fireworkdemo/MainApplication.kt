package com.loopnow.fireworkdemo

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings
import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.loopnow.fireworklibrary.FwSDK
import com.loopnow.fireworklibrary.SdkStatus
import com.loopnow.fireworklibrary.utils.UserLoginStatus
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@SuppressLint("HardwareIds")
class MainApplication : Application() {
    companion object {
        val fwInitialized = MutableLiveData(false)
    }

    private val clientId =  "f6d6ec1275217f178cdff91363825cb390e038c1168f64f6efa23cb95ec6b325" //"f6d6ec1275217f178cdff91363825cb390e038c1168f64f6efa23cb95ec6b325"
    private val hashedUserId by lazy {
         getHash(Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        ))
        //"PJ2398K"
    }
    override fun onCreate() {
        super.onCreate()
        FwSDK.initialize(this, clientId, hashedUserId, object : FwSDK.SdkStatusListener {
            override fun currentStatus(status: SdkStatus, extra: String) {
                when(status) {
                    SdkStatus.Initialized -> {
                        fwInitialized.value  = true
                    }
                }


                Log.v("StatusLog", "$status  ->>> $extra")
            }
        })

    }

    private fun getHash(text: String) : String {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val hash: ByteArray = digest.digest(text.toByteArray(StandardCharsets.UTF_8))
        Log.v("StatusLog", " ${text.length}  and  ${Base64.encodeToString(hash, Base64.DEFAULT).length} ")
        return Base64.encodeToString(hash, Base64.DEFAULT)
    }
}