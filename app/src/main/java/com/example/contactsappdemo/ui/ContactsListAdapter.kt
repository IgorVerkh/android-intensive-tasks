package com.example.contactsappdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsappdemo.databinding.ListViewItemBinding
import com.example.contactsappdemo.model.Contact

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class ContactsListAdapter(
    private val clickListener: ContactListener,
    ) :
    ListAdapter<Contact, ContactsListAdapter.ContactViewHolder>(DiffCallback) {

    class ContactViewHolder(
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ContactListener, contact: Contact) {
            binding.contact = contact
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.lastName == newItem.lastName &&
                    oldItem.phoneNumber == newItem.phoneNumber &&
                    oldItem.profilePhoto == newItem.profilePhoto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(clickListener, contact)
    }

}

class ContactListener(val clickListener: (contact: Contact) -> Unit,
                      val onLongClickListener: (contact: Contact) -> Boolean) {
    fun onClick(contact: Contact) = clickListener(contact)
    fun onLongClick(contact: Contact) = onLongClickListener(contact)
}