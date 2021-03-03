### FireworkSDK
FireworkSDK is a library to integrate video feeds from ```Firework - a short form video platform``` into your Android application. 

### FireworkSDK Demo 
https://github.com/loopsocial/firework_sdk_official/blob/master/FireworkDemo.apk

### Prerequisites 
To integrate FireworkSDK into your application, you have to register your application with Firework platform and get unique
clientId. To get the clientId 

- [X] Provide your application's applicationId / package name to the business team / engineering team you are co-ordinating with. If your applicationId is different from package name, provide applicationId.
- [X] Our team will create clientId and share it with you.

The clientId is used to authenticate your application with the server. Authentication will fail if your application's applicationId / package name is different from what you provided, or you use wrong clientId. 
 
### How to add library to your project? 

Please select the appropriate version of firework sdk library. We currently support ExoPlayer version v2.9.6, v2.11.7, v2.12.1.

https://github.com/loopsocial/firework_sdk_official/blob/master/RELEASENOTES.MD


- [X] In AndroidManifest.xml, add 

		<application>	
		....
		....
				// Activity needed for video playback
				<activity
					android:name="com.loopnow.fireworklibrary.PlaybackActivity"
					android:screenOrientation="portrait"
					android:theme="@style/FireworkSDK.NoActionBar.FullScreen" />
			
				// Activity needed for starting a web browser , when CTA on the advertisement is clicked. 
				<activity
					android:name="com.loopnow.fireworklibrary.views.FireworkWebClientActivity"
					android:theme="@style/AppTheme.NoActionBar.FullScreen" />


				// Service used for prefetching of next video , if all data for currently playing video is fetched. 
				// If you do not want to enable prefetching, you can exclude thsi line from AndroidManifet.xml 
				<service android:name="com.loopnow.fireworklibrary.views.CacheService" />
				
			

				// We plan on using advertising_id to improve target ads so that you can monetize better. 
				// This is needed for to get advertising id using Android ad sdk.  
				<meta-data
					android:name="com.google.android.gms.ads.AD_MANAGER_APP"
					android:value="true" />
			
		</application>
    
- [X] In your application's build.gradle, add 

		dependencies {
			------ other dependencies 
			------ 
			------
			implementation 'com.github.loopsocial:firework_sdk:{latest_version}' 
			
			// Refer to https://github.com/loopsocial/firework_sdk_official/blob/master/RELEASENOTES.MD

		}

		android {
			.....
			.....
		
			dataBinding {
				enabled = true
			}

			compileOptions {
				sourceCompatibility 1.8
				targetCompatibility 1.8
	     		}
		}
	
- [X] In your project's build.gradle, add 
	
	
		allprojects {
			repositories {
			
				--
				---
				--- 
				maven { url 'https://jitpack.io' }
			}
		}
	
- [X] In proguard-rules.pro, add 
		
		-keepclassmembers class com.loopnow.fireworklibrary.** { <fields>; }


### Initializing Firework SDK 
You have to initialize Firework SDK before you can use any of its features. You should initialize Firework SDK when application is launched/created. 

 FwSDK.initialize(
        applicationContext,
        clientId,
        userId,
        sdkStatusListener
 )
 
 - applicationContext : provide application context , we don't require activity context. 
 - clientId           : Pass the clientId provided to you by the business team you are working with. 
 - userId(optional)   : An id to uniquely identify device or user. In case you pass null, Android_ID is used.
                        If id passed is not unique, it will affect the quality of content recommended to the user. 
 - sdkStatusListener  : Implementation of interface SdkStatusListener to track sdk events. 


### Integrating video feed in your application.  

### VideoFeedView 

You can integrate video feed in your application by adding VideoFeedView to your view hiearachy. VideoFeedView is a custom android view. You can specify of of three layouts for the VideoFeedView. 

- Vertical
- Horizontal
- Grid


Here is an XML snippet of VideoFeedView that you can customize to your needs and add to your view hierarchy. 


			<com.loopnow.fireworklibrary.views.VideoFeedView
	   			android:layout_width="{desired_width}"
	   			android:layout_height="{desired_height}"
	   			app:showTitle="{true or false}"
	   			app:feedLayout="{grid | horizontal | vertical}"
	  			app:columns="{number_of_columns_if_your_feedLayout_is_grid, default value is 2}"
	   			app:textStyle="@style/{your_text_style_video_title}"
				app:imageStyle="@style/{your_image_style_video_thumbnail}"
				app:feedType="{discover | channel}"
				app:feedParam="{channel id when feedType is channel}"
				app:feedId={integer id to uniquely identify feed, it is different from view id}
			/> 
			
			

### Required Attributes

[x] android:layout_width : Specify the basic width of the view, this is required attribute. 
[x] android:layout_height : Specify the basic height of the view, this is required attribute. 
[x] app:feedId : Any integer value to uniquely identify video feed.


### Optional Attributes

[x] app:feedLayout : This attribute specifies the layout for displaying video feed. The possible values are 

- Grid
- Horizontal
- Vertical

The default value is Horizontal
		
a. Grid :  It layouts video feed in a multiple ```<rows> x <columns>``` format and scrolls vertically. The default column value is 2. 
you can specify columns with xml attribute ```app:columns``` 	
		
<img src="screenshots/grid.jpg"  width="270" height="480"> <img src="screenshots/grid_with_title.jpg"  width="270" height="480">
			
			
b. horizontal : It layouts video feed as a single row that works as a horizontal scrollable view.
		  
<img src="screenshots/Horizontal_video_list.png"  width="270" height="480">
	
c. vertical : It layout video feed as a single column and functions as a vertical scrollable view.
		
<img src="screenshots/vertical.jpg"  width="270" height="480"> <img src="screenshots/vertical_with_title.jpg"  width="270" height="480">
	
We recommend using layout_height="match_parent" when feedLayout is specified as either Vertical or Grid. Use definite height defined either as % of the parent viewgroup's height or specified in terms of absolute value in dp when feedLayout is Horizontal 
	       
	       e.g 
	       
	       1. feedLayout="vertical" or feedLaout="grid"
	          layout_height="match_parent"
		  
	       2. feedLayout="horizontal" 
	       	  layout_height="200dp" 
		  
		  or if you are using ConstraintLayout as a parent layout. 
		  
		  layout_height="0dp" 
		  app:layout_constraintHeight_default="percent"
              	  app:layout_constraintHeight_percent="0.40"
		 
[x] app:columns : Use it to specify the number of columns for Grid layout. 

[x] app:showTitle : Use it to display video caption. If true, video caption is displayed, hidden otherwise. The default value is false.  The position of the title is controlled by the attribute ```app:titlePosition```. The text style applied to title, can be specified with optional attribute ```app:textStyle```. 

[x] app:textStyle : Provide custom textStyle to be applied to TextView displaying video caption. Please refer below for an example and usage. 
	
	```app:textStyle="@style/VideoTitleStyle"```

	<style name="VideoTitleStyle">
        	<item name="android:textColor">#ff4a4a4a</item>
        	<item name="android:textSize">14dp</item>
        	<item name="android:lines">2</item>
	        <item name="android:maxLines">2</item>
        	<item name="android:gravity">left</item>
        	<item name="android:layout_width">match_parent</item>
        	<item name="android:fontFamily">@font/squeakychalk</item>
   	</style>
  

[x] app:titlePosition : When attribute showTitle is set to true,  app:titlePosition="alignBottom" will align the bottom of TextView displaying title to the bottom of the thumbnail and app:titlePosition="below" will align the top of the TextView displaying title to the bottom of the thumbnail. 


[x] imageStyle : You can specify corner radus by providing image style. Refer to an example below. The default radius is 10dp 

```app:imageStyle="@style/ThumbnailStyle"```

	<style name="ImageStyle" >
	       <item name="android:radius">12dp</item>
	</style>
	
[x] app:gutterSpace : When you use layout="grid", gutterSpace is the space between two consecutive columns & rows. By default it is 8dp but you can customize it. 

		<com.loopnow.fireworklibrary.views.VideoFeedView"                  
			app:gutterSpace="{your_desired_value e.g 4dp}"
        		/>


[x] app:enableShare : When set to true, ```Share``` feature is enabled. User can click on the share icon displayed at the bottom of the video to share the video url via apps that support sending text/url. It is enabled by default. You can disable it by setting it to false. 

[x] app:autoPlayOnFeed : You can enable autoplaying of the video without volume by setting app:autoPlayOnFeed to true. It is set to false by default.   

[x] app:autoPlayOnComplete :  When set to true, the next video will start playing as soon as currently playing video finishes playing. When it is set to false, the currently playing video continues to play in the loop.  

[clip] app:clip : it controls the clipToPadding property of the recyclerView. Set it to true if you want to set clipToPadding to true , false otherwise. Default is false. 

### Event Callsback 


[x] Sdk Status Callback


interface SdkStatusCallback {
  fun currentStatus(status : SdkStatus, extra: String) 
}

enum class SdkStatus() {

    - Initialized, // Sdk Initialized 
    - InitializationFailed, // Sdk initialization failed, in this case you shouldn't add VideoFeedView to your view hiearchy. Extra will describe the error. 
    - LoadingContent, // Sdk is requesting content from the server 
    - LoadingContentFailed, // Failed to load content, extra will have number of videos present in the feed at the time when error occured 
    - ContentLoaded // Content loaded successfully, this is called just before data is presented to the user.

}


[x] Playback Event Callback 
 

interface VideoEventListener {
    fun event(eventName: String, jsonObject: JSONObject)
}

events : 

- "video-impression" : Fired when video is shown to the user 
- "video-start" : Fired when video has played 1 second 
- "video-first-quartile" : Fired when 25% of the video is played.  
- "video-midpoint" : Fired when 50% of the video is played. 
- "video-third-quartile" : Fired when 75% of the video is played. 
- "video-complete" : Fired when 100% of the video is played. 
- "video-session-end" : Fired when video is no more on the screen, it is possible for a video to loop after 100% playack is complete, in which case, video-exit and video-complete will occur at different times. 
- "video-ad-start" : Fired when ad video has played 1 second 
- "video-ad-end" : Fired when ad video finishes playing 
- "video-click-cta" : Fired when user has clicked on the CTA ( not all video will have CTA ) 
- "videoShare" : Fired when user has clicked on the share button
- "videoStartError" : Fired when video playback encountered an error 
- "videoAdStartError" : Fired when ad video playback encountered an error 

Along with each event, you will also get the relevant data in the form of JSONObject. The data includes 

"autoplay" : Boolean  -> describes if the video playack is autoplay
"badge" : String -> badge will have value "ad" , if the playing video is ad video 
"caption" : String -> title of the video playing
"duration" : Long -> total duration of the video in miliseconds 
"hashtags" : StringArray -> hashtags associated with the video 
"has_cta" : Boolean -> does video have CTA ? 
"height" : Int -> height of the video player 
"width" : Int -> width of the video player 
"video_id" : String -> encodedId of the video with which it is uniquely identified 
"progress" : progress of video playback in miliseconds when the event occured 


You can add VideoEventListener as below 

	FwSDK.addVideoEventListener(object: FwSDK.VideoEventListener {
            override fun event(eventName: String, jsonObject: JSONObject) {
                
            }
        })


[x] Item clicked callback 

interface 
You can listen to, when a user clicks on one of the video thumbnails in the feed to start watching the video. 

	interface OnItemClickedListener {
    		fun onItemClicked(index: Int, title: String, id: String, videoDuration: Long) 
	}
	
    
    	videoFeedView.addOnItemClickedListener(object: OnItemClickedListener {
                    override fun onItemClicked(
                        index: Int,
                        title: String,
                        id: String,
                        videoDuration: Float
                    ) {
                        Log.v("EventLog", " $index $title $id $videoDuration")
                    }
                })

	
### Rewarded Video Callback 

	Called when user watches videos for enough duration and earn a reward
	
	interface RewardListener {
        	fun rewardEarned(encodedId: String?, rewardAmount: Int)
    	}
    
	// Example 
        FwSDK.addRewardListener(object: FireworkSDK.RewardListener {
            override fun rewardEarned(encodedId: String?, rewardAmount: Int) {
            }
        })


### Common Errors 

1. When user clicks on one of the thumbnails from the video feed integrated in your application, FireworkSDK handles the onClick event and starts the video 	playback. If you have not already added PlaybackActivity to your AndroidManifest file, you should. The application will crash without it. 

2. Blank feed - If there is mismatch between application id you provided  or app_id is wrong, authentication will fail and you won't see anything on the screen . You can check this condition using adb logcat | grep NetworkLog 

3. Blank feed with Invalid channel id Toast - If feedType is set to channel and feedParam is invalid, you won't get video feeds. You can check your feedParam and make sure it is valid. 





