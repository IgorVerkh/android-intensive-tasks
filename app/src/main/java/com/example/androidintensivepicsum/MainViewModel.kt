package com.example.androidintensivepicsum

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidintensivepicsum.network.UrlImageDownloader
import kotlinx.coroutines.launch
import java.io.IOException

enum class DownloadImageStatus {ERROR, DONE}

class MainViewModel: ViewModel() {

    private val _link = MutableLiveData<String>()
    val link: LiveData<String> = _link

    private val _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap> = _image

    private val _status = MutableLiveData<DownloadImageStatus>()
    val status: LiveData<DownloadImageStatus> = _status

    private val imageDownloader = UrlImageDownloader()

    fun loadImage() {
        viewModelScope.launch {
            val bitmap = imageDownloader.loadImage(_link.value.toString())
            if (bitmap != null) {
                setImage(bitmap)
                _status.value = DownloadImageStatus.DONE
            } else {
                _status.value = DownloadImageStatus.ERROR
            }
        }
    }

    fun setLink(link: String) {
        _link.value = link
    }

    private fun setImage(bitmap: Bitmap) {
        _image.value = bitmap
    }
}