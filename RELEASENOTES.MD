# Release notes #
## v4.6.7 ( 2021-01-15) 
1. Internationalization
2. Bug fixes
3. ExoPlayer version v2.11.7


## v4.7.0 ( 2020-01-16) 
This is v4.6.7 release with exoplayer v2.9.6

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
