package com.example.androidintensivepicsum.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class UrlImageDownloader() {

    suspend fun loadImage(address: String): Bitmap? {

        return withContext(Dispatchers.IO) {
            var bitmap: Bitmap? = null

            try {
                val url = URL(address)
                val urlConnection = url.openConnection() as HttpURLConnection

                val inputStream = BufferedInputStream(urlConnection.inputStream)
                bitmap = BitmapFactory.decodeStream(inputStream)

                urlConnection.disconnect()
            } catch (e: IOException) {

            } catch (e: MalformedURLException) {

            }
            finally {

            }
            bitmap
        }

    }
}