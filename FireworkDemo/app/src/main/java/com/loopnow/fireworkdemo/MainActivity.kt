package com.loopnow.fireworkdemo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.loopnow.fireworkdemo.ui.CheckoutActivity
import com.loopnow.fireworkdemo.ui.main.SectionsPagerAdapter
import com.loopnow.fireworklibrary.*
import com.loopnow.fireworklibrary.baya.Baya
import com.loopnow.fireworklibrary.baya.UpdateCartStatus
import com.loopnow.fireworklibrary.data.Product
import com.loopnow.fireworklibrary.models.VideoContentStatus
import com.loopnow.fireworklibrary.utils.Util
import com.loopnow.fireworklibrary.views.OnVideoContentStatusListener
import kotlinx.coroutines.*

import org.json.JSONObject
import kotlin.coroutines.CoroutineContext


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), CoroutineScope {
    private val parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        VideoPlayerProperties.fullScreenPlayer = true

        // if activity is launched due to user clicking on the deeplink
        // then refer to the code below for how to handle it and launch
        // the player.

        val uri: Uri? = intent.data

        uri?.let {
            val openDeepLink = uri.scheme ?: "" in listOf(
                "http",
                "https"
            ) && uri.host == "yourbasedomain.com"

            // Until SDK is initialized
            MainApplication.fwInitialized.observe(this, {
                if (it && openDeepLink) {
                    val parts = uri.toString().split("fwplayer=")
                    if (parts.size > 1) {
                        FwSDK.play(parts[1])
                    }
                }
            })
        }

        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.offscreenPageLimit = 5
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(p0: Int) {
                sectionsPagerAdapter.setCurrentPage(p0)
            }
        })*/

        FwSDK.addVideoEventListener(object : FwSDK.VideoEventListener {
            override fun event(event: String, jsonObject: JSONObject) {
                // placyback events and json payload
                // refer to documentation for event details and payload
                // https://docs.firework.tv/android-sdks/android
                // tab playback events
                val duration = jsonObject.optInt("duration")
                val currentPos = jsonObject.optDouble("progress")

                Log.v("VideoEvent: ", "$event ->  $duration  :   $currentPos : $jsonObject   " )
            }
        })

        // This is how you would customize the share url which when shared
        // can be handled as deeplink
        FwSDK.setBasePlayerUrl("https://yourbasedomain.com")

        // If you were to handle the CTA click,
        // Implement CtaClickHandler

        FwSDK.ctaClickHandler = object : FwSDK.CtaClickHandler {
            /**
             * return true if you handled, false if you want SDK to handle it.
             */
            override fun ctaClicked(label: String, actionUrl: String?): Boolean {
                return false
            }
        }

        addOnPlayerVideoContentStatusListener()

        //Shoppable video interface.
        addBAYA()

        //Live stream callbacks.
        addLiveStreamListener()
    }

    private fun addLiveStreamListener() {
        FireworkLiveStream.addFireworkLiveStreamEventListener(object: FireworkLiveStreamEventListener {
            override fun userJoined(liveStreamDetails:FireworkLiveStreamEventDetails) {
                //Trigger when user join the live stream.
            }

            override fun userLeft(liveStreamDetails:FireworkLiveStreamEventDetails) {
                //Trigger when user leave the live stream.
            }

            override fun userSentChat(chatDetails: FireworkLiveStreamChatDetails) {
                //Trigger when livestream receives a chat message.
            }

            override fun userSentLike(liveStreamDetails: FireworkLiveStreamEventDetails) {
                //Trigger when livestream receives a LIKE click.
            }
        })
    }

    private fun addBAYA() {
        Baya.willUseCustomCart(true)
        Baya.cartInterface = object: Baya.CartInterface {

            var productIdStr: String? = null
            var unitIdStr:String? = null

            override fun updateCart(
                activity: Activity,
                productId: String,
                unitId: String,
                quantity: Int
            ) {
                Toast.makeText(this@MainActivity, "******* (SDK msg) Adding productId:$productId & unitId:$unitId to cart.   *******", Toast.LENGTH_LONG).show()

                launch {
                    delay(2000)
                    Baya.updateCartStatus(UpdateCartStatus.Success("Host app msg: Added to cart.", unitId))
                    Baya.itemCount.value = 1
                    productIdStr = productId
                    unitIdStr = unitId
                    Toast.makeText(this@MainActivity,
                        "******* (SDK msg) You just added productId:$productId & unitId:$unitId to cart.  *******", Toast.LENGTH_LONG).show()
                }
            }

            override fun cartClicked(activity: Activity) {
                val checkoutIntent = Intent(activity, CheckoutActivity::class.java)
                checkoutIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                activity.startActivity(checkoutIntent)
                overridePendingTransition(0, 0)
            }
        }

        Baya.productInterface = object: Baya.ProductInterface {
            override fun hydrateProducts(videoId: String, products: List<Product>) {
                launch {
                    delay(5000)
                    for (product in products) {
                        product.name = "new product name"
                        product.description = "This is modified product description from the host app."
                        for (productUnit in product.productUnits) {
                            productUnit.price = 200.05
                            productUnit.currencyCode = "USD"
                            productUnit.priceLiteral = Util.getPriceLocaleFormatter(productUnit.price, productUnit.currencyCode)
                        }
                        val productOptionMapHash = product.optionsMap["Frame Color"]
                        productOptionMapHash?.add("Color Yellow")
                    }
                    Baya.updateProductsComplete(videoId)
                }
            }

            override fun displayProductInfo(
                productId: String,
                unitId: String,
                productWebUrl: String?
            ): Boolean {
                Log.v("ProductLog", " display product info ")
                return false
            }
        }
    }

    private fun addOnPlayerVideoContentStatusListener() {
        FwSDK.addOnPlayerVideoContentStatusListener(object: OnVideoContentStatusListener {
            override fun currentStatus(status: VideoContentStatus, extra: String) {
                when (status) {
                    VideoContentStatus.LoadingContentFailed -> {
                        Log.v("VideoContentStatus", "player status: ${VideoContentStatus.LoadingContentFailed}, extra: $extra")
                    }
                    VideoContentStatus.ContentLoaded -> {
                        Log.v("VideoContentStatus", "player status: ${VideoContentStatus.ContentLoaded}, extra: $extra")
                    }
                }
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}

