package com.example.pc.grainchainapp.ui.main.newContact

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pc.grainchainapp.Application
import com.example.pc.grainchainapp.R
import com.example.pc.grainchainapp.db.ContactEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_new_contact.*
import kotlinx.android.synthetic.main.fragment_new_contact.view.*

class NewContactFragment : Fragment() {

    private val RESULT_LOAD_IMAGE = 4785
    private var profilePicture : Uri ? = null
    private lateinit var view1: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view1 = inflater.inflate(R.layout.fragment_new_contact, container, false)
        setHasOptionsMenu(false)
        initializeListeners()
        return view1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            profilePicture = data?.data!!
            Picasso.get().load(profilePicture).resize(view1.add_profile_buton.measuredWidth, view1.add_profile_buton.measuredHeight).into(view1.add_profile_buton);

        }
    }

    private fun createNewContact(userCreated : ContactEntity){
        Application.getContactsEntityBox().put(userCreated)
        Toast.makeText(context, R.string.fragment_new_contact_save_button_toast, Toast.LENGTH_SHORT).show()
        clearView()
    }

    private fun clearView(){
        add_profile_buton.setImageResource(R.mipmap.ic_placeholder)
        profilePicture = null
        user_name.text = null
        user_last_name.text = null
        user_age.text = null
        user_phone.text = null

        view1.clearFocus()
    }

    private fun initializeListeners(){
        view1.add_profile_buton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,RESULT_LOAD_IMAGE)
        }

        view1.save_contact_button?.setOnClickListener{
            if( user_name.text.isNullOrEmpty() || user_last_name.text.isNullOrEmpty() || user_age.text.isNullOrEmpty() || user_phone.text.isNullOrEmpty()) {
                Toast.makeText(context, R.string.fragment_new_contact_save_button_toast_error, Toast.LENGTH_SHORT).show()
            } else{
                createNewContact(
                    ContactEntity(
                        userName = user_name.text.toString(),
                        lastName = user_last_name.text.toString(),
                        age = user_age.text.toString(),
                        phoneNumber = user_phone.text.toString(),
                        picUrl = profilePicture.toString()
                    )
                )
            }
        }
    }
}