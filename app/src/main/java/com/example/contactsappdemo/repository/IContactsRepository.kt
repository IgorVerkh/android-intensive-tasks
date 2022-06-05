package com.example.contactsappdemo.repository

import com.example.contactsappdemo.model.Contact

interface IContactsRepository {
    fun fetchContactsList(): List<Contact>
}