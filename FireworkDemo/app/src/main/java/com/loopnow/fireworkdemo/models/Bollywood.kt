package com.loopnow.fireworkdemo.models



data class Bollywood(val content: List<DemoContent>)

interface DemoContent {
    var layout: Int
}

data class Content (val url: String, val text: String, override var layout: Int) : DemoContent
data class VideoFeed(val keywords: String, override var layout: Int ) : DemoContent


