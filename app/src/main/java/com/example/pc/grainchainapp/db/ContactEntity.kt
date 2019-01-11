package com.example.pc.grainchainapp.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ContactEntity(


    @Id
    var id : Long = 0,

    val userName: String,

    val lastName: String,

    val age: String,

    val phoneNumber: String,

    val picUrl : String)