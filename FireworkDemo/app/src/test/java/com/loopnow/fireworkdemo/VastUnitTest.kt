package com.loopnow.fireworkdemo


import android.R.attr.order
import android.content.Context
import com.loopnow.fireworklibrary.FireworkSDK
import com.loopnow.fireworklibrary.vast.VASTProcessor
import com.loopnow.fireworklibrary.vast.model.TRACKING_EVENTS_TYPE
import com.loopnow.fireworklibrary.vast.model.VASTModel
import com.loopnow.fireworklibrary.views.EventReportingHelper
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class VastUnitTest {


    val fireworkSDK: FireworkSDK = mock()
    val loopCount = 0

    lateinit var helper: EventReportingHelper
    private val videoDuration: Long = 28048

    @Before
    fun setUp() {

        val context: Context = mock()
        helper = EventReportingHelper(context)
        helper.videoDuration = videoDuration
        helper.fireworkSDK = fireworkSDK

        //doNothing().when(fireworkSDK.reportVastEvent(""))


    }

    @Test
    fun test_Progress() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model
        model.prepareProgressData()

        model.trackingUrls.clear()

        val models = ArrayList<VASTModel>()
        models.add(model)
        var lastPosition: Long  = 0

        for( currentPosition: Long in 1000L until videoDuration step 16) {

            lastPosition = helper.handleVastEvents(videoDuration, currentPosition, lastPosition , loopCount, models)

            if(model.htOffsetDurations.containsKey(currentPosition)) {
                val expected = model.htOffsetDurations[currentPosition] ?: ""
                verify(fireworkSDK).reportVastEvent(expected)
            }
            //System.out.println("UiLog : key = " + currentPosition  )

        }

    }


    @Test
    fun test_startEvent() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        prepareVast(TRACKING_EVENTS_TYPE.start,model)
        val models = ArrayList<VASTModel>()
        models.add(model)

        val currentPosition: Long = 1000

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.start]?.get(0) ?: ""
        //val expected = models[0].htOffsetDurations[key] ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }

    @Test
    fun test_createView() {

        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        //model.prepareProgressData()

        prepareVast(TRACKING_EVENTS_TYPE.creativeView,model)

        val models = ArrayList<VASTModel>()
        models.add(model)

        val currentPosition: Long = 1000


        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.creativeView]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }

    @Test
    fun test_firstQuartile() {

        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        //model.prepareProgressData()

        prepareVast(TRACKING_EVENTS_TYPE.firstQuartile,model)

        val models = ArrayList<VASTModel>()
        models.add(model)


        val currentPosition: Long = (videoDuration * 0.25).toLong()

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.firstQuartile]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)

    }



    @Test
    fun test_midPoint() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        //model.prepareProgressData()

        prepareVast(TRACKING_EVENTS_TYPE.midpoint,model)

        val models = ArrayList<VASTModel>()
        models.add(model)
        val currentPosition: Long = (videoDuration * 0.50).toLong()

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.midpoint]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)

    }

    @Test
    fun test_thirdQuartile() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        //model.prepareProgressData()

        prepareVast(TRACKING_EVENTS_TYPE.thirdQuartile,model)

        val models = ArrayList<VASTModel>()
        models.add(model)
        val currentPosition: Long = (videoDuration * 0.75).toLong()

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.thirdQuartile]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }

    @Test
    fun test_complete() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model

        //model.prepareProgressData()

        prepareVast(TRACKING_EVENTS_TYPE.complete,model)

        val models = ArrayList<VASTModel>()
        models.add(model)
        val currentPosition: Long = (videoDuration ).toLong()

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.complete]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }


    @Test
    fun test_mute() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model
        prepareVast(TRACKING_EVENTS_TYPE.mute,model)

        EventReportingHelper.isMute = true

        val models = ArrayList<VASTModel>()
        models.add(model)
        val currentPosition: Long = 1000

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.mute]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }

    @Test
    fun test_unmute() {
        val vastProcessor = VASTProcessor()
        vastProcessor.process(testXmlStr)
        val model = vastProcessor.model
        prepareVast(TRACKING_EVENTS_TYPE.unmute,model)

        EventReportingHelper.isMute = false
        val models = ArrayList<VASTModel>()
        models.add(model)
        val currentPosition: Long = 1000

        helper.handleVastEvents(videoDuration, currentPosition, 0, loopCount, models)
        val expected = models[0].trackingUrls[TRACKING_EVENTS_TYPE.unmute]?.get(0) ?: ""
        verify(fireworkSDK).reportVastEvent(expected)
    }


    private fun prepareVast(event: TRACKING_EVENTS_TYPE, vastModel: VASTModel) {
        val urls = vastModel.trackingUrls[TRACKING_EVENTS_TYPE.start]
        vastModel.trackingUrls.clear()
        vastModel.trackingUrls[TRACKING_EVENTS_TYPE.start] = urls
    }




    private val testXmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<VAST version=\"3.0\">\n" +
            "\t<Ad id=\"video-oedMlB\">\n" +
            "\t\t<InLine>\n" +
            "\t\t\t<Impression>\n" +
            "\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/impression/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t</Impression>\n" +
            "\t\t\t<AdSystem>Firework Video Server</AdSystem>\n" +
            "\t\t\t<AdTitle>\n" +
            "\t\t\t\t<![CDATA[Thought we made the best LockDown drink, but this!]]>\n" +
            "\t\t\t</AdTitle>\n" +
            "\t\t\t<Error>\n" +
            "\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/error/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t</Error>\n" +
            "\t\t\t<Creatives>\n" +
            "\t\t\t\t<Creative id=\"oedMlB\">\n" +
            "\t\t\t\t\t<Linear>\n" +
            "\t\t\t\t\t\t<Duration>00:00:29</Duration>\n" +
            "\t\t\t\t\t\t<TrackingEvents>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"close\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/close/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"complete\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/comp100p/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"creativeView\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/creative_view/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"firstQuartile\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/comp25p/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"midpoint\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/comp50p/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"mute\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/mute/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"pause\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/pause/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:03\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/engaged_view/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"resume\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/resume/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"start\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/comp0/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"thirdQuartile\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/comp75p/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"unmute\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/unmute/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:01\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MSZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz02czgtdmEycGU2MnBtWVczdS1GTGtFWDJOaGJaNS1pbHdNSnNvVWJEbURn]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:02\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1xa3VhMzZTbWIxTjZ1QURKb0VIZ0VQbjV3dWFaZHo0UXczZ3NLU00yc29R]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:03\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MyZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1DcktXNUtzZEd1RGo1U25HTU0xd2dySGp6SW85WUpSSlZkNUhZTTZIakFZ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:04\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9NCZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz05TlJMakdjUGJNS1N6cGs0a0JfN0czMWViMTloZVpZZldyVE90V3RwZ1ZN]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:05\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9NSZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1FSWlCVDFTSExUUmxpeWpYYUFVYnlRYmlFZ1pINllIU2w2U1k5dWlCT2p3]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:06\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9NiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1aS0FYUkJwc0dENmdyeFV6V3I0eEtEbWY1LWMyZUI5WmtTT1ZPQUlXaWJR]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:07\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9NyZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1heWdpeWpKby1lSGJCNHdXN0Y2OGRBM0dQZWVHUjQxS0VacjVReUEtZ3Q0]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:08\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9OCZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1IeC1iMUFlbGZyUU0yN3AzcVdHQXpEcnlIRW10SlhPRjlJTzVpNVFPZnJB]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:09\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9OSZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1rYW5Ndl8yUTB1UUU0V3hnU2t2aENTc1ZVejBHXzdjV0FrUG9jMWthTWh3]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:10\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTAmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9V2hiTzZfN0RiX1FkRC00dzRBODdEOEwwVTVMTkFuOVFPZ0l3ZmthdlV5NA]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:11\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTEmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9a0ROVXRPMm95Vjl5MFl5S0dJRnphcEY2X2x3MTVxeEVVRlRxai01QlZYTQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:12\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTImdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9VW13NnRpcmltbzJiX0hrRnpOTWdOZXJuRjlINy0tb3p0U0xGbHhxYW9wdw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:13\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTMmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9NjZXTGNPOEVQWFAxRl9mYjJZTUx6alIyLVBOT3BDakM1dnNNSk80ZW4ybw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:14\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTQmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9eEx1b2dGRmdReGhnU29zd3ZCajFGRmh5OG9LaWpXSDFzR190Mnl3T1RQbw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:15\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTUmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9X1RjYTRMYUNvNWdld0NNeGxoZy11Vm50UGFEV2Q4N1JSUzdKd3A2VHl0Zw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:16\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTYmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9OFlhR2xqZm5tXzF4VkxkSEh6RDd4MjQwNnozWVFLcmhqNk5BZXFxeldhNA]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:17\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTcmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9WjdPVVM3MGl2emlndlpzZEVYV1VPN3RNRGIzWUFPdkFNb0NxTno5M0V4SQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:18\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTgmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9Um1FRjJ6WmhUUzVJWllMRlpfWkdTWjBmaWtUaG12d2FnSVlKV0l4NVpOSQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:19\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MTkmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9TmhGRTlmN3UtejJLNk51OFlTazZnRUtUUHBkRGZhT1N2dE1XNWZvMHV6dw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:20\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjAmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9ZFVOYjlhZ3EtMnpQWmYzY0NMUFFSNGdaVzhVcVozUlUtQ3hKM1lRckxFOA]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:21\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjEmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9a0RLQnpxeWtTUzdrUmNoQnBIRmV5ZVFnTDRfQVdCWlJkSVZEWHZWWl9vUQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:22\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjImdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9RHdiUENtV1BBdHNsbFlhaGsyV1E0dXF2M0t2Sk8zS3U2OEo5Z0lDY2lsNA]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:23\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjMmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9Q0JRc3QxYXRkLWJQWlBsc3VUY1V2RFFXZ3pEaXctc2cxR2tFeGlsRWJqcw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:24\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjQmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9RUZmNkt3aGJtaDJDWVIxTEJ5SkZvaXJ5YWtuTXUzMU1LcWR2SDZHR05xTQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:25\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjUmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9bVlUWkV1M0M4ZnJCZVlGcHdmaGV2OFVKN3Y0VGhZaDVPb2RjOTQ2cHNsYw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:26\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjYmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9ekt0MDE5S2NWVUxVeWd6UFVsRmRlUk52bU9PQVRDVnhzMWF0VTJWTVpaWQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:27\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjcmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9X1UtcU1XYThRemtxUnNQSkVfTTVqb3pTSE52cHhnWmN1blhLSldLUVVTaw]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:28\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjgmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9M1M5ZlJybzdZNlNpRWxGNFRiMzhzUG9VUTFfOWlYRDZpZ0l3LUR0YXd1TQ]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t\t<Tracking event=\"progress\" offset=\"00:00:29\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/progress/X3ZpZGVvX2lkPW9lZE1sQiZzZWNvbmRzX3dhdGNoZWQ9MjkmdHM9MTU5MjQyOTQ3NSZ2aWRlb19wbGF5X2lkPTE1OTI0Mjk0NzU3NTI0NzAtM2wyZ3I5d3cxem9rbWZsOXF6N2dkb2tkaCZ2aXNpdG9yX2lkPTA0MjUxZTkxLWFhYTktNDhiNC05MThlLWM3ZTA5MDU1YjgyNSZzaWc9SVdVVFp1SnN2c2VLZkFndFRWQ0M3Y0JwY3k0SkJzeS14SUdEeXQ2YUtiMA]]>\n" +
            "\t\t\t\t\t\t\t</Tracking>\n" +
            "\t\t\t\t\t\t</TrackingEvents>\n" +
            "\t\t\t\t\t\t<VideoClicks>\n" +
            "\t\t\t\t\t\t\t<ClickTracking>\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[https://p1-staging.fwpixel.com/vid/click/X3ZpZGVvX2lkPW9lZE1sQiZ0cz0xNTkyNDI5NDc1JnZpZGVvX3BsYXlfaWQ9MTU5MjQyOTQ3NTc1MjQ3MC0zbDJncjl3dzF6b2ttZmw5cXo3Z2Rva2RoJnZpc2l0b3JfaWQ9MDQyNTFlOTEtYWFhOS00OGI0LTkxOGUtYzdlMDkwNTViODI1JnNpZz1RTElLLVVNd1Q5MmZ2N0dYcURqREwyMC1tR094TktVMkExSjk4NHNKdlY0]]>\n" +
            "\t\t\t\t\t\t\t</ClickTracking>\n" +
            "\t\t\t\t\t\t</VideoClicks>\n" +
            "\t\t\t\t\t\t<MediaFiles>\n" +
            "\t\t\t\t\t\t\t<MediaFile id=\"FireworkAdServer\" delivery=\"progressive\" width=\"1920\" height=\"1080\" type=\"video/mp4\" scalable=\"true\" maintainAspectRatio=\"true\">\n" +
            "\t\t\t\t\t\t\t\t<![CDATA[\n" +
            "                    https://cdn1-staging.fireworktv.com/medias/2020/4/2/1585821287-lbpurkhg/watermarked/000/20200402115156.mp4\n" +
            "                  ]]>\n" +
            "\t\t\t\t\t\t\t</MediaFile>\n" +
            "\t\t\t\t\t\t</MediaFiles>\n" +
            "\t\t\t\t\t</Linear>\n" +
            "\t\t\t\t</Creative>\n" +
            "\t\t\t</Creatives>\n" +
            "\t\t</InLine>\n" +
            "\t</Ad>\n" +
            "</VAST>\n"


}