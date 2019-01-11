package com.example.pc.grainchainapp.ui.login

import com.example.pc.grainchainapp.web.entities.UserLoginEntity

interface LoginContract {
    interface View{
        fun obtainLoginInfo(): UserLoginEntity
        fun loginSuccessful(userName : String)
        fun toggleLoading()
        fun loginErrorDialog()
    }

    interface Presenter{
        fun userLogin()
    }
}