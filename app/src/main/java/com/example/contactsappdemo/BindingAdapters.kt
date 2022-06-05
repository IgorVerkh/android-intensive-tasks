package com.example.contactsappdemo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsappdemo.model.Contact
import com.example.contactsappdemo.ui.ContactsListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Contact>?) {
    val adapter = recyclerView.adapter as ContactsListAdapter
    adapter.submitList(data)
}