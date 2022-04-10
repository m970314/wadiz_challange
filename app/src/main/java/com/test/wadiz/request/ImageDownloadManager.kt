package com.test.wadiz.request

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object ImageDownloadManager {
    val imagecache = mutableMapOf<String, Bitmap>()
    fun loadImage(url: String, completed: (Bitmap?) -> Unit) {
        //TODO: String -> Bitmap 을 구현하세요
        if(url.isEmpty()) {
            completed(null)
            return
        }
        if(imagecache.containsKey(url)) {
            completed(imagecache[url])
            return
        }
        GlobalScope.launch {
            try{
                val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
                imagecache[url] = bitmap
                withContext(Dispatchers.Main){
                    completed(bitmap)
                }
            } catch (e : Exception){
                withContext(Dispatchers.Main){
                    completed(null)
                }
            }
        }
    }
}