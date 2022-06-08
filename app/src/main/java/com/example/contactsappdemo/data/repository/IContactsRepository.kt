package com.example.contactsappdemo.data.repository

import com.example.contactsappdemo.model.Contact

interface IContactsRepository {
    fun fetchContactsList(): List<Contact>
}