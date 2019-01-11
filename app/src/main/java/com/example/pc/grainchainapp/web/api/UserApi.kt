package com.example.pc.grainchainapp.web.api

import com.example.pc.grainchainapp.web.entities.UserLoginEntity
import com.example.pc.grainchainapp.web.entities.UserLoginResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {

    @POST("/test")
    fun loginUser(@Body loginData: UserLoginEntity)
            : Observable<UserLoginResponseEntity>
}