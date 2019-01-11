package com.example.pc.grainchainapp.web.service

import com.example.pc.grainchainapp.web.api.UserApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AwsServiceGenerator {

    private var apiBaseUrl = "https://kdmldkvxoe.execute-api.us-west-2.amazonaws.com/"

    var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val createUserService = retrofit.create<UserApi>(UserApi::class.java)
}