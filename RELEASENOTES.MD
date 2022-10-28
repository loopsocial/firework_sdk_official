# Release notes #

## v5.14.5

* [Android Minimum Support] : 21

### [Improvement]:
* Talkback annoucement on the player view componements.
* Add product impressions events.
* Extended product "Add to cart" timeout to 10s.


## v5.14.0

* [Android Minimum Support] : 21

### [Features]:
* Introduce a new programmatically feed layout: Storyblock. Learn more from [integration doc](https://docs.firework.tv/android-sdks/android/storyblock).
* Customize ADA announcement.

### [Improvement]:
* RTL support follows with device language but not application language.
* UIs: add space between currency symbol and amount for shoppable videos.

### [Bug fixes]:
* Feed refresh() doesn’t function properly on the dynamic content feed type.
* The live stream chat box placeholder always shows the same username. 
* Mute/unmute control should be on the player level but not the system media level.


## v5.13.0

* [Android Minimum Support] : 21

### [Features]:
* Support Arabic translations.
* Support RTL.
* Support product unit option hydration in shoppable videos.

### [Improvement]:
* Chat messages capabilities enhancement for taking care of robust message volume on active live streams. 

### [Bug fixes]:
* Video fit mode: The top of player view got cut off by dismiss status bar UI issue on Android 10 or earlier Android version devices. 


## v5.12.3

* [Android Minimum Support] : 21

### [Bug fixes]:

* Fixed autoplay doesn't play properly after introducing livestream feature.
* Fixed multiple out of index exceptions relate to videos with products.


### [Improvements]:

* Display the corresponding product unit option thumbnail while users select other unit options.
* Add video title to onItemClick() in playlist group feed type.
* Support playlist group custom layout.


## v5.12.0

* [Android Minimum Support] : 21


### [Feature]:

* Introduce a new programmatically feed type: Dynamic content. Learn more from [integration doc](https://docs.firework.tv/android-sdks/android/video-feed/dynamic-content).

### [Improvements]:

* Disable system volume toast while volume changes.
* Fixed reveal video height doesn't fit screen height on the video fit mode.



## v5.11.5

* [Android Minimum Support] : 21

### [Bug]:
* Fixed crash caused by quickly double tapping videos with AD in feed.



## v5.11.4

* [Android Minimum Support] : 21

### [Feature]:
* Support livestream playback and callbacks.
  [See the guidelines on the "Playback events" tab](https://docs.firework.tv/android-sdks/android)
  

## v5.10.3

* [Android Minimum Support] : 21

### [Bugs]:
* Fixed CTA button dismiss due to product payload issue.


## v5.10.1

* [Android Minimum Support] : 21

### [Feature]:
* Support player mute/unmute default configuration.
[See the guidelines on the "VideoPlayer" tab](https://docs.firework.tv/android-sdks/android)

### [Bugs]:
* Fixed feed autoplay crash issue on the certain corner cases.
* Fixed deek link display mismatch video issue.


## v5.10.0

* [Android Minimum Support] : 21

### [Features]:
* Support Ad label show/hide configuration on the feed.
[See the guidelines on the "VideoFeedView" tab](https://docs.firework.tv/android-sdks/android)

* Support customize Ad label to "Sponsored" or "Ad".
[See the guidelines on the "VideoFeedView" & "Video Player" tab](https://docs.firework.tv/android-sdks/android)


### [Bug]:
* Fixed missing tracking event on Custom Handling of CTA function. 


## v5.9.4

* [Android Minimum Support] : 21

### [Bug]:
* Fixed the crash due handler being null after onDetachedView is called on feed autoPlay mode.


## v5.9.0

* [Android Minimum Support] : 21

### [Features]:
* Support playlist group feed.
[See the guidelines on the "Integrate VideoFeed" tab](https://docs.firework.tv/android-sdks/android)


### [Improvements]:
* Separate sdk initialize callbacks and video feed status callbacks.
* Support asynchronous updates on hydratProduct().
* Support GuestUid updates on SDK **initialize()** and deprecate **removeGuestUid()**
[See the deprecate info on the "Changelog" tab](https://docs.firework.tv/android-sdks/android)


### [Bugs]:
* Fixed baseUrl doesn't apply to fullscreen player mode.
* Fixed Feed doesn't load when swiping to load the next pagination.



## v5.8.0

* [Android Minimum Support] : 21

### [Features]:
* Support playback control: mute/unmute, play/pause.
[See the guidelines on the "Video Player" tab](https://docs.firework.tv/android-sdks/android)
* Support custom product link function.
[See the guidelines on the "BAYA" tab](https://docs.firework.tv/android-sdks/android)
* Support Exoplayer 2.16.1.

### [Improvements]:
* Expose external product id & unit id to BAYA.CartInterface.



## v5.7.1

* [Android Minimum Support] : 21

### [Features]:
* Support the shoppable video with multiple products.
* Support "CTA" and "Add to cart" button customization.
  [See the button customization from the SDK integration Guide on the "VideoPlayer" tab & "Customize style" section](https://docs.firework.tv/android-sdks/android)


## v5.6.4

* [Android Minimum Support] : 21

### [Bugs]:
* Correct the data type of feed click reporting event.


## v5.6.3

* [Android Minimum Support] : 21

### [Features]:

* Support for feed refresh function.
 [See the details from the SDK integration Guide on the "VideoFeedView" tab & "Functions" section](https://docs.firework.tv/android-sdks/android)

### [Bugs]:
* Fixed VideoFeedView & VideoFeedAdapter execption issues.

### [Improvements]:
* Added new state "RefreshTokenFailed" to SdkStatus().
  
## v5.6.0

* [Android Minimum Support] : 21

### [Features]:

* Support FWAI AD insertion method.
* Support playlist AD config.
* Support CTA translation from backend and detect the translation by the phone default language.


### [Bugs]:
* Fixed IMA AD displays the black screen on preroll & interstitial placement type.
  This fixed
  
[Note]: the IMA AD solution requires AndroidFireworkPlayer upgrades follow by below version: "AndroidFireworkPlayer:v2.13.3.8", "AndroidFireworkPlayer:v2.12.1.8"
and "AndroidFireworkPlayer:v2.11.7.8".

[See the details from the SDK integration Guide on the "Setup" tab & "appication's build.gradle file" section](https://docs.firework.tv/android-sdks/android)

## v5.5.10

* [Android Minimum Support] : 21

### [Bugs]:
* Address low crash rate issue due to report thumbnail tracking events under the slow network connection situation.


## v5.5.9

* [Android Minimum Support] : 21

### [Features]:

* Add a unique id for the Rewarded AD.
* New API for clearing the cached guest_id. [See the details from the SDK integration Guide on the "Setup" tab & "Clear Guest Id" section](https://docs.firework.tv/android-sdks/android)

### [Improvements]:
* Deprecated **FireworkWebClientActivity** for the CTA web page. Start from v5.5.9, developers don't need to add **FireworkWebClientActivity** into Manifest.xml anymore.

### [Bugs]:
* User can now click CTA button to view AD details web page, when close it then returns to the same ad instead of moveing to the next video.
* User can return from AD details web page and use tap to go to the next/previous video.



## v5.5.8

* [Android Minimum Support] : 21

### Improvements
* Fixed the detail view doesn't apply the share property.


## v5.5.6

* [Android Minimum Support] : 21

### Improvements
* Improved interstital ad interval consistency.
* Improved the detail view UIs on the smaller screen.
* Improved the IMA experience on swiping, Skip AD button.

[Note]: the IMA improvements require AndroidFireworkPlayer upgrades follow by below version: "AndroidFireworkPlayer:v2.13.3.4", "AndroidFireworkPlayer:v2.12.1.4"
and "AndroidFireworkPlayer:v2.11.7.4".

Pleae refer to https://docs.firework.tv/android-sdks/android  and tab setup for more details.


## v5.5.5

* [Android Minimum Support] : 21

### Improvements
* Dismiss the loading indicator when the feed reaches the end.
* Fixed video event duration bug.
* Fixed Google Banner Ad memory leak issue.


## v5.5.4

* [Android Minimum Support] : 21

### Improvements

* Fixed the firework SDK memory leak issues. 
* Fixed API connection timeout issue under the bad network connectivity environment.
* Fixed duplicate Ads issue when user tap on the video thumbnail and close the player multiple times.


## v5.5.3

* [Android Minimum Support] : 21

### Feature 
[Checksum] : As a developer you can now choose to add checksum to event tracking in case your are concerned about anyone corrupting the data. To enable checksum you only need to add `<meta-data android:name="Firework:Checksum" android:value="true" />` to your AndroidManifest file. 


## v5.5.2

* [Android Minimum Support] : 21 


### Improvement
* [Ad Config] : new communication protocol 
* [play-service] : You can provide the play-services dependency , instead of picking up Firework SDK supporting specific version of play-services. Pleae refer to https://docs.firework.tv/android-sdks/android  and tab setup for more details. 



## v5.5.1

* [Android Minimum Support] : 21 
* [play-services-ads] : 19.5.0

### Improvement
* [Video Player] : We improved the player UX 
* [ExoPlayer] : You can provide the exoplayer dependency , instead of picking up Firework SDK supporting specific version of Exoplayer. Pleae refer to https://docs.firework.tv/android-sdks/android  and tab setup for more details. 
* [guest_id] : We now persist the guest_id you pass during initialization. In the subsequent app launches, if you pass the different guest_id, SDK will continue to use the guest_id , you used the first time. 

### Bug Fixes
* [Visitor Events] : We fixed the error that caused some of the events reporting to not get fired. 



## v5.4.7

* [Exoplayer Version] : v2.13.3
* [Android Minimum Support] : 19 
* [play-services-ads] : 19.5.0

### Improvement
* [FwSDK] : We changed the definition of initialize method. Earlier, one of the arguments `userId` was @Nullable. It is now must be non null. Please refer to the documentation.  Tab : Setup , Section: Intitializing SDK . https://docs.firework.tv/android-sdks/android


## v5.4.5

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21 
* [play-services-ads] : 19.5.0

## v5.4.4

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21 
* [play-services-ads] : 19.5.0


### Improvement
* [WebView] : Stopped handling SSLError and instead being delegated to super class. 

## v5.4.3

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21 
* [play-services-ads] : 19.5.0


### Bug Fix
* [Player Background Color] :  Changed player background color to #404040 from white. 



## v5.4.2

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21 
* [play-services-ads] : 19.5.0


### Bug Fix
* [Player Background Color] :  Changed player background color to #404040 from white. 


## v5.4.1

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21 

### Bug Fix
* [Fullbleed Mode] :  Crash on Android 6 and lower fixed.


## v5.4.0
* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21 

### Features
[Storyblock] : You can now directly launch the video player and configure the content ( no need for intermediate thumbnail view that user needs to click on ) 
[ad] : Support for IMA SDK 



## v5.3.7

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21

### Improvements
* [Lifecycle] :  Changes that eliminates explicitly calling FwSDK.onDestroy()



## v5.3.4

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21

### Features
* [CTA : clickHandler] : Provies application an option to handle CTA click


## v5.3.3

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Features
* [CTA : clickHandler] : Provies application an option to handle CTA click



## v5.3.2

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Bug Fix
* [Networking] : Improved exception handling   
* [Video Swipe] : Addressed race condition that will swipe 2 videos in place of just one video 



## v5.3.1

* [Exoplayer Version] : v2.12.1
* [Android Minimum Support] : 21

### Feature 
* [ShareUrl] : You can now set the base url for the share url , for example https://example.com , When base url is set and user shares the video, 
   a custom url is shared. When anyone clicks on this url, you have opportunity to handle it in your own app and use FwSDK api  play(encodedParameters: String)
   to play video in your app, rather than playing it in the browser. 
   


### Bug Fix
* [Networking] : Fixed uncaught exceptions - SocketTimeoutException, HostNotFoundException  


## v5.3.0

* [Exoplayer Version] : v2.11.7
* [Android Minimum Support] : 21

### Feature 
* [ShareUrl] : You can now set the base url for the share url , for example https://example.com , When base url is set and user shares the video, 
   a custom url is shared. When anyone clicks on this url, you have opportunity to handle it in your own app and use FwSDK api  play(encodedParameters: String)
   to play video in your app, rather than playing it in the browser. 
   


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
