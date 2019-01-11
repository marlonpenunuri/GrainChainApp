package com.example.pc.grainchainapp.ui.main

interface MainContract {
    interface View

    interface Presenter{
        fun getContacts()
        fun postContacts()
    }
}