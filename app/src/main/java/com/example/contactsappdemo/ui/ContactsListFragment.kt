package com.example.contactsappdemo.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.contactsappdemo.R
import com.example.contactsappdemo.databinding.FragmentContactsListBinding
import com.example.contactsappdemo.model.Contact


class ContactsListFragment : Fragment() {

    private val viewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = ContactsListAdapter(ContactListener (
            { contact ->
                onListItemClick(contact)
            },
            { contact ->
                onListItemLongClick(contact)
                true
            }
        ))
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onListItemLongClick(contact: Contact) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Delete"
                ) { dialog, id ->
                    // User clicked OK button
                    viewModel.deleteContact(contact)
                }
                setNegativeButton("Cancel"
                ) { dialog, id ->
                    // User cancelled the dialog
                }
                setMessage("Delete \'${contact.name} ${contact.lastName}\' contact?")
            }
            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    private fun onListItemClick(contact: Contact) {
        viewModel.onContactClicked(contact)
        findNavController()
            .navigate(R.id.action_contactsListFragment_to_contactDetailFragment)
    }

}