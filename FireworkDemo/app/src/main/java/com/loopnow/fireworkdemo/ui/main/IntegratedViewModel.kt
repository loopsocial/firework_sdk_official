package com.loopnow.fireworkdemo.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.WorkerThread
import com.loopnow.fireworkdemo.R
import com.loopnow.fireworkdemo.models.Content
import com.loopnow.fireworkdemo.models.DemoContent
import com.loopnow.fireworkdemo.models.VideoFeed

open class IntegratedViewModel : ViewModel() {
    val intengratedContent = MutableLiveData<List<DemoContent>>()

    fun getContent()  : LiveData<List<DemoContent>> {
        if(intengratedContent.value == null ) {
            Thread {
                prepareContent()
            }.start()
        }
        return intengratedContent
    }

    @WorkerThread
    open fun prepareContent() {
        val contentFeed = ArrayList<DemoContent>()


        for(i in 0 until Math.min(imageUrls.size,textContent.size) -1 ) {

            contentFeed.add( Content(imageUrls[i], textContent[i], R.layout.content_item))

            if(i == 1 ) {
                contentFeed.add(VideoFeed(keywords[i], R.layout.videofeed_item))
            }


        }

        intengratedContent.postValue(contentFeed)
    }

    val imageUrls = arrayOf(
            "http://www.bollycon.com/wp-content/uploads/2019/10/WhatsApp-Image-2019-10-25-at-16.48.26-768x1151.jpeg",
            "http://www.bollycon.com/wp-content/uploads/2019/10/Rajeev2.jpg",
            "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057340135818731938053789474.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057429083992818906685148988.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057360014525797959607797575.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057319012994130065060951885.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057449754598149751922034297.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057386571135322885642595647.jpg",
        "http://www.bollycon.com/wp-content/uploads/2019/11/fb_img_15725057407652314747492065374904.jpg"
    )
    var textContent  = arrayOf(
            "The Housefull 4 actress had said in an interview how she was quite inspired by Deepika Padukone…\n" +
                    "\n" +
                    "With lots of praises coming her way, Kriti Kharbanda’s bid with Housefull 4 has turned out to be a fruitful one. Yes, while this is the first time Kriti is seen in this big a mass entertainer, her performance is being loved by many including co-star Akshay Kumar. But it seems, not just Akshay, but another leading lady from Bollywood has all praises for Kriti Kharbanda. And it is none other than Kriti’s inspiration Deepika Padukone.\n" +
                    "\n" +
                    "After Kriti said in an interview (to Hindustan Times) how she was inspired by Alia Bhatt and Deepika Padukone for the roles they played, Deepika also responded in the sweetest way possible. The Chhappaak actress wrote back to Kriti saying, “Dearest Kriti, Thank you for the love! All the best for Housefull 4 and for everything else that you do! Wishing you good health and peace of mind always! Lots of love, Deepika.” Isn’t it amazing to see how our B-town ladies don’t hold back from admiring each other’s work these days?",
            "Renowned actor, singer and host, Rajeev Khandelwal reveals his struggles in Bollywood for the first time on Firework. The social video app has launched its first Firework original for India – Reel, Reveal, Rajeev, featuring the struggles, aspirations and the real life of Rajeev Khandelwal in 30 second episodes. The actor, Rajeev Khandelwal, who recently celebrated his birthday, is giving his viewers an ‘inside view of his world’. Through these episodes, Firework aims to inspire users with stories of inspiration, accomplishment and persistence. Users can interact with the star on a weekly basis by following his Firework account.",
            "In his prolific film career as a successful writer-director spanning over three decades, Anees Bazmee has blessed Hindi cinema and movie aficionados with blockbuster comedies full of memorable characters, namely No Entry, Welcome franchise, Singh Is Kinng, No Problem, Ready, Thank You and Mubarakan among others.",
        "Fondly referred to as the ‘King of Comedy’, the creative genius has also written several money-spinners including Shola Aur Shabnam, Bol Radha Bol, Aankhen, Raja Babu and Mujhse Shadi Karogi to name a few, thereby creating his unique brand of comedy over the years and still successfully maintaining the same with his next offering, the highly-anticipated multi-starrer comedy, Pagalpanti featuring Anil Kapoor, John Abraham, Arshad Warsi, Pulkit Samrat, Ileana D’Cruz, Kriti Kharbanda and Urvashi Rautela.",
        "Anees informs, “Being a writer has really helped me work on so many movies in the comedy genre. I probably wouldn’t have been able to do that if I was just a director and worked on other person’s idea or story. I have always maintained that it’s easy to create buffoonery with nudity and double meaning dialogues but scoring a hit with a clean comedy that a family can sit together and enjoy is a real challenge.”",
        "The monstrous hit maker who has a string of comedies lined up for the next year explains, “Comedy is a very serious business, and interestingly not many are aware that most of the comedy films that I have made so far, I’ve written them when I was not in the best state of mind, or in an unwell condition.”",
        "Recollecting an interesting anecdote which also shows his professionalism and dedication towards his craft, Bazmee reveals, “When I was shooting Welcome, I was unwell for a particular schedule and when Nana (Patekar) got to know about my ill health, he was a little worried and asked me to rest. But I told him that I’ll be okay if I keep working and later when I was editing the film, my assistants told me how I shot that particular scene when I was not keeping well. As Raj Kapoor sahab said, ‘the show must go on’.”",
        "Like his successful comedies, the characters that he creates are equally crazy, hilariously entertaining and memorable, be it Uday-Majnu bhai and RDX in Welcome or Prem and Kishan in No Entry. His highly-anticipated multi starrer laugh riot, Pagalpanti is no different! “Shooting for the movie was nothing less than a roller-coaster ride. I’m sure the audience will enjoy the crazy characters in the movie, be it Anil Kapoor’s Wifi bhai, John Abraham’s Raj Kishore, Arshad as Junky and Pulkit as Chandu. The most important challenge is to ensure that all the actors that you work with have faith in you and your vision as a filmmaker. I’m grateful that the actors that I have worked with have always had that belief in me. Maybe it’s out of their love and respect,” Anees concludes.",
            ""
    )

    val keywords = arrayOf(
            "Halloween",
            "Bollywood",
            "Bollywood Dance",
            "Dance India",
            "Horror",
            "Halloween Makeup",
            "Scary"
    )







}

