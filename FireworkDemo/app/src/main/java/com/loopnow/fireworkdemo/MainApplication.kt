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
    /**
     * This is the client_id that you must have received from one of Firework operators. You must pass this
     * in the intialize method.
     * NOTED: Below clientId only uses for the demo app, it's not for your application production usage.
     */
    private val clientId = "e8f03f8a614629c53d9200c1f57e723005440268b4ff70538757d265c2e0487d"

    companion object {
        val fwInitialized = MutableLiveData(false)
    }

    /**
     * Firework SDK requires you pass the unique id for each of the users using
     * your application. If you don't create a unque id for each user in your system
     * you can use Android_ID. You can hash Android_ID in case there is any privacy concern.
     * You can refer to the the function below to hash the Android_ID
     */
    private val hashedUserId by lazy {
         getHash(Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        ))
    }

    /**
     * returns the hashed value of the string text
     * @param text string to be hashed
     */
    private fun getHash(text: String) : String {
       /* val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val hash: ByteArray = digest.digest(text.toByteArray(StandardCharsets.UTF_8))
        Log.v("StatusLog", " ${text.length}  and  ${Base64.encodeToString(hash, Base64.DEFAULT).length} ")
        return Base64.encodeToString(hash, Base64.DEFAULT)
        */
        return text
    }

    /**
     * Initialize firework SDK in onCreate method.
     * @param FwSdkStatusListener receive the SDK status update with the callback
     * FwSDK.SdkStatusListener
     *
     */
    override fun onCreate() {
        super.onCreate()
        FwSDK.initialize(this, clientId, hashedUserId, object : FwSDK.SdkStatusListener {
            override fun currentStatus(status: SdkStatus, extra: String) {
                Log.v("SdkStatusLog", "$status   -> $extra " )
                when(status) {
                    SdkStatus.Initialized -> {
                        fwInitialized.value  = true
                    }
                }
            }
        })
    }
}