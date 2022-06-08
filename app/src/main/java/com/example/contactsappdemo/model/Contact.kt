package com.example.contactsappdemo.model

import android.graphics.Bitmap

data class Contact(
    val id: Int,
    val name: String,
    val lastName: String,
    val phoneNumber: String,
    val profilePhoto: String
)
