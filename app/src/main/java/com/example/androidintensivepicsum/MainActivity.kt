package com.example.androidintensivepicsum

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageLink = findViewById<EditText>(R.id.image_link)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val imageObserver = Observer<Bitmap> { newImage ->
            imageView.setImageBitmap(newImage)
        }
        viewModel.image.observe(this, imageObserver)

        val statusObserver = Observer<DownloadImageStatus> { newStatus ->
            if (newStatus == DownloadImageStatus.ERROR) showErrorToast()
        }
        viewModel.status.observe(this, statusObserver)

//        imageLink.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if (URLUtil.isValidUrl(p0.toString())) {
//                    updateImage(imageLink)
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//        } )

        // change enter button label just because
        imageLink.setImeActionLabel("Load", KeyEvent.KEYCODE_ENTER)

        // edit text enter key listener
        imageLink.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // if the event is a key down event on the enter button
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    updateImage(imageLink)
                    return true
                }
                return false
            }
        })

    }

    private fun showErrorToast() {
        Toast.makeText(this, "Couldn't download the image", Toast.LENGTH_SHORT).show()
    }

    private fun updateImage(editText: EditText) {
        viewModel.setLink(editText.text.toString())
        viewModel.loadImage()

    }
}

