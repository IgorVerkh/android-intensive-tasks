package com.example.contactsappdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.contactsappdemo.R
import com.example.contactsappdemo.databinding.FragmentContactsListBinding


class ContactsListFragment : Fragment() {

    private val viewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactsListBinding.inflate(inflater)

        viewModel.fetchContacts()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = ContactsListAdapter(ContactListener { contact ->
            viewModel.onContactClicked(contact)
            findNavController()
                .navigate(R.id.action_contactsListFragment_to_contactDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}