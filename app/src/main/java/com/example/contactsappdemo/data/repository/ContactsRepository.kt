package com.example.contactsappdemo.data.repository

import com.example.contactsappdemo.data.datasource.FakeContactsDataSource
import com.example.contactsappdemo.model.Contact

class ContactsRepository : IContactsRepository {
    override fun fetchContactsList(): List<Contact> {
        return FakeContactsDataSource.generateData()
    }
}