package com.example.contactsappdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsappdemo.model.Contact
import com.example.contactsappdemo.data.repository.ContactsRepository

class ContactsViewModel : ViewModel() {

    private val contactsRepository = ContactsRepository()

    private val _contactsList = MutableLiveData<List<Contact>>()
    val contactsList: LiveData<List<Contact>> = _contactsList

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    init {
        if (_contactsList.value.isNullOrEmpty()) fetchContacts()
    }

    fun fetchContacts() {
        _contactsList.value = contactsRepository.fetchContactsList()
    }

    fun onContactClicked(contact: Contact) {
        _contact.value = contact
    }

    fun deleteContact(contact: Contact) {
        val mutableContactList = _contactsList.value?.toMutableList()
        mutableContactList?.remove(contact)
        _contactsList.value = mutableContactList?.toList()
    }
}