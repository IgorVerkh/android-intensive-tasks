package com.example.contactsappdemo.repository

import com.example.contactsappdemo.model.Contact

class ContactsRepository : IContactsRepository {
    override fun fetchContactsList(): List<Contact> {
        return mockContactsList
    }
}

val mockContactsList = listOf(
    Contact(0, "Jessika", "Rosamund", "+44 20 7946 0154"),
    Contact(1, "Clyde", "Dieudonné", "+33 07 61 58 75 83"),
    Contact(2, "Clifton", "Shayla", "+44 1632 960262"),
    Contact(3, "Helmuth", "Mandi", "+44 29 9496 0692")
)