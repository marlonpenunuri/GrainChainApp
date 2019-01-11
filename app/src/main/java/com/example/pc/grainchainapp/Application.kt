package com.example.pc.grainchainapp

import io.objectbox.BoxStore
import android.app.Application
import com.example.pc.grainchainapp.Application.Companion.boxStore
import com.example.pc.grainchainapp.db.ContactEntity
import com.example.pc.grainchainapp.db.MyObjectBox
import io.objectbox.Box


class Application : Application() {

        companion object {
            lateinit var boxStore: BoxStore

            fun getContactsEntityBox(): Box<ContactEntity> {
                return boxStore.boxFor<ContactEntity>(ContactEntity::class.java)
            }
        }

        override fun onCreate() {
            super.onCreate()
            boxStore = MyObjectBox.builder().androidContext(this).build()

        }

    }








