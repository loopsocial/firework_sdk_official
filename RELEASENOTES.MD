# Release notes #


## v5.3.0

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Feature 
* [ShareUrl] : You can now set the base url for the share url , for example https://example.com , When base url is set and user shares the video, 
   a custom url is shared. When anyone clicks on this url, you have opportunity to handle it in your own app and use FwSDK api  play(encodedParameters: String)
   to play video in your app, rather than playing it in the browser. 
   setBaseUrl(baseUrl: String) 
   play(encodedParameters: String) 


### Bug Fix
* [Networking] : Fixed uncaught exceptions - SocketTimeoutException, HostNotFoundException  



## v5.2.7

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Bug Fix
[Android O] : Crashed was caused on all devices with Android veresion less than O, it is fixed 



## v5.2.5

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21

### Bug Fix
[Android O] : Crashed was caused on all devices with Android veresion less than O, it is fixed 


## Previous Versions 

## v5.2.2

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Enhancements

* [Video Player] : Change the user interface of the player, videos now play true to their aspect ratio. 
* [Channel Playlist] : Added support for getting video feed from the playlist owned by a particular channel 
* [API] : Added support to programatically set feedType, channelId and playlistId 

### API details 

* fun setChannel(channelId: String, feedType: FeedType)
* fun setPlaylist(channelId: String, playlistId: String, feedType: FeedType) 


## v5.2.0


* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Bug Fix

* [user_id] : Fixed bug where Firework user_id was sent to lambda instead of guest_id ( provided by application ) 


## v5.1.9

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Bug Fix

* [authorized] : Race condition resulted in VideoFeedView not knowing about authorization status. It resulted in feed not being loaded.


## v5.1.7 

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21

### Bug Fix 
* [authorized] : Race condition resulted in VideoFeedView not knowing about authorization status. It resulted in feed not being loaded. 


## v5.1.6

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21  

### Bug Fix 
* [Rewards] : Rewards turned off that were turned on by default.  



## v5.1.4 

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21  

### Enhancements 
* [Api Added] : Api added to set channel_id at runtime in addition to compile time which is already supported via XML 


## v4.7.7 (2020-01-18) Recommended ## ( inherits 4.7.1 )
1. Bug fixes 
2. ExoPlayer versino v2.11.7


## v4.7.1 (2020-01-18) Recommended ## ( inherits 4.3.9 )
1. Bug fixes
2. ExoPlayer versino v2.11.7


## v4.7.0 (2020-01-16) 
This is v4.6.7 release with exoplayer v2.9.6

## v4.6.1 (2020-01-05)
Support cleaning up listener objects.

For example:
```
   1.FireworkSDK.addVideoPlaybackStatusListener(null)
   2.FireworkSDK.addSdkStatusListener(null)
   3.FireworkSDK.addVideoPlaybackTracker(null)
```

## v4.3.9 (2020-12-03) Recommended ## ( inherits 4.3.1 )
1. Bug fixes
2. GAM support
3. Improved response time by decoupling video request and ad request 
4. ExoPlayer version v2.11.7


## v4.2.9 (2020-11-9)   

@Deprecated VideoFeedAdapter.OnItemClickListener 

instead use OnItemClickedListener 
For Example 

videoFeedView.addOnItemClickedListener(object: OnItemClickedListener()  {
   
})

## v4.2.5 (2020-10-22) ( inherits 4.2.3 ) 

* Added callback for SdkStatus

      FireworkSDK.addSdkStatusListener(object: FireworkSDK.SdkStatusListener {
            override fun currentStatus(status: SdkStatus, extra: String) {
                Log.v("UiLog", " ${status.name}   $extra ")
            }
        })

      Status Details : 
      
      Ok : Sdk initialized 
      Error : Sdk failed to initialize 
      ErrorLoadingContent : Error encountered while loading video feed
      LoadingCotent :  Content is Loading ( i.e request is made and waiting for server to respond )  
      VideoFeedOk : Video Feed Successfully Loaded
      
* Exo Player v2.11.7 

* Programatically load content for a category 
            
            for example. 
            videoView.loadContent("entertainment")
 
            If you want to programatically set category and load content, it is recommended that you use attribute
                        
                 app:loadContent="false"  
                 
            int your xml. This will prevent default content being loaded until you decide to load it programatically. 
 
 

## v4.2.3 (2020-10-01) 
* EVerything from v4.2.0
* Support added for attribute app:category in VideoFeedView. This will only display videos from a given category. 
   
        <com.loopnow.fireworklibrary.views.VideoFeedView
            android:id="@+id/integrated_videofeed"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:feedLayout="horizontal"
            app:category="Food"
    
            />
            
## v4.2.0 (2020-09-28)

*   You can disable autoplay with xml attribute app:autoPlayOnFeed="false" and enable it with app:autoPlayOnFeed="true"
    
*   Libraries 
    
    * Exoplayer v2.11.7
    * AndroidX 
    
## v4.0.4 - variant of v4.0.0 ##

   * With Exoplayer r2.2.2 instead of v2.11.3, appropriate if you are already using Exoplayer r2.2.2 in your application. 
    

## v4.1.5 - variant of v4.2.0 ##
    
    * with Exoplayer v2.11.7 
    * Added attribute category in VideoFeedView 

## v3.0.2 (2020-06-24) ##

* What's new? 
  
    * Improved Tracking
    * Deprecated VideoFeedFragment and added VideoFeedView

*   Libraries     
    * Exoplayer v2.11.3
    * AndroidX 




# 版本通知 #

## v4.0.0 (2020-07-29) 建議版本 ##

*   Admob Ads: 支援Admob原生及橫幅廣告。
    根據您的需求設定原生或橫幅廣告類別:
    
    * 原生廣告: 原生廣告會在用戶端觀看了 `n` 個影片後出現，您可以自行定義 `n`。
    * 橫幅廣告: 橫幅廣告會在用戶端觀看了同一影片`n`次後出現，您可以自行定義 `n`。
    
*   Libraries 
    
    * Exoplayer v2.11.3
    * AndroidX 
    
## v4.0.1 - variant of v4.0.0 ##

   * 若已使用Exoplayer r2.2.2則建議保留Exoplayer r2.2.2，無需更動至v2.11.3。
    

## v3.0.2 (2020-06-24) ##

* What's new? 
  
    * 資料分析修復
    * 移除 VideoFeedFragment 並且新增 VideoFeedView

*   Libraries     
    * Exoplayer v2.11.3
    * AndroidX 
