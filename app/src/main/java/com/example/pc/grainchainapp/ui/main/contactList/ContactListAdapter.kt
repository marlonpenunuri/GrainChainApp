package com.example.pc.grainchainapp.ui.main.contactList

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.example.pc.grainchainapp.R
import com.example.pc.grainchainapp.db.ContactEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contact_card.view.*
import com.example.pc.grainchainapp.Application
import com.example.pc.grainchainapp.db.ContactEntity_


class ContactListAdapter(contacts: MutableList<ContactEntity>, mContext: Context?) :
    RecyclerView.Adapter<ContactListAdapter.CustomViewHolder>() {
    private var contactsArray: MutableList<ContactEntity> = contacts

    //number of item
    override fun getItemCount(): Int {
        return contactsArray.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //create view
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedItem = layoutInflater.inflate(R.layout.item_contact_card, parent, false)
        return CustomViewHolder(inflatedItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val contact = contactsArray[position]
        holder.setData(contact, position)


    }

    inner class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val mContext = view.context

        init {
            view.setOnLongClickListener {
                MaterialDialog.Builder(mContext)
                    .title(mContext.getString(R.string.app_name))
                    .titleColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
                    .content(mContext.getString(R.string.dialog_delete_contact))
                    .positiveText(mContext.getString(R.string.accept))
                    .positiveColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
                    .onPositive { _, _ ->
                        contactsArray.removeAt(position)
                        val removedUser = Application.getContactsEntityBox().query()
                            .equal(ContactEntity_.phoneNumber, itemView.contact_phone.text.toString()).build().find()
                        Application.getContactsEntityBox().remove(removedUser)
                        notifyDataSetChanged()
                    }
                    .negativeText(mContext.getString(R.string.cancel))
                    .negativeColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
                    .show()
                return@setOnLongClickListener true
            }
        }

        fun setData(contact: ContactEntity, pos: Int) {
            val userFullName = contact.userName + " " + contact.lastName
            view.contact_name.text = userFullName
            view.contact_age.text = contact.age
            view.contact_phone.text = contact.phoneNumber

            val contactProfileView = view.contact_profile_pic
            Picasso.get().load(contact.picUrl).placeholder(R.mipmap.ic_placeholder).resize(100, 100).centerCrop()
                .into(contactProfileView)
        }
    }

}




