package com.example.pc.grainchainapp.ui.main.contactList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.example.pc.grainchainapp.Application
import com.example.pc.grainchainapp.R
import com.example.pc.grainchainapp.db.ContactEntity
import com.example.pc.grainchainapp.db.ContactEntity_

class ContactListFragment : Fragment() {
    private var contactsDisplayed = mutableListOf<ContactEntity>()
    private var contacts = mutableListOf<ContactEntity>()
    private var mAdapter: ContactListAdapter? = null

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)

        initializeContacts()
        initializeAdapter(view)

        setHasOptionsMenu(true)
        return view
    }

    private fun initializeContacts() {
        Application.getContactsEntityBox().removeAll()
        contactsDisplayed.clear()

        val namesArray = resources.getStringArray(R.array.contacts_name)
        val lastNamesArray = resources.getStringArray(R.array.contacts_lastname)
        val ageArray = resources.getStringArray(R.array.contacts_age)
        val phoneArray = resources.getStringArray(R.array.contacts_phone)
        val picArray = resources.getStringArray(R.array.contacts_profile)
        var index = 0

        namesArray.forEach {
            val newUser = ContactEntity(
                userName = it,
                lastName = lastNamesArray[index],
                age = ageArray[index] + " " + resources.getString(R.string.fragment_contacts_card_years),
                phoneNumber = phoneArray[index],
                picUrl = picArray[index]
            )
            Application.getContactsEntityBox().put(newUser)
            index++
        }
    }

    private fun initializeAdapter(view : View){
        contactsDisplayed = Application.getContactsEntityBox().query().order(ContactEntity_.userName).build().find()
        recycler = view.findViewById(R.id.contacts_recycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = ContactListAdapter(contactsDisplayed, context)
        mAdapter = recycler.adapter as ContactListAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.contact_list_menu, menu)
        val searchView = menu.findItem(R.id.menu_nav_search_bar)
        val searchItem = searchView.actionView as SearchView

        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                contacts = Application.getContactsEntityBox().query().order(ContactEntity_.userName).build().find()
                if (newText!!.isNotEmpty()) {
                    contactsDisplayed.clear()

                    val search = newText.toLowerCase().trim()
                    contacts.forEach {
                        val fullName = it.userName.toLowerCase() + " " + it.lastName.toLowerCase()
                        if (fullName.toLowerCase().contains(search)) {
                            contactsDisplayed.add(it)
                        }
                    }
                    mAdapter?.notifyDataSetChanged()
                } else {
                    contactsDisplayed.clear()
                    contactsDisplayed.addAll(contacts)
                    mAdapter?.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser){
            contacts = Application.getContactsEntityBox().all
            contactsDisplayed.clear()
            contactsDisplayed.addAll(contacts)
            mAdapter?.notifyDataSetChanged()
        }
    }


}