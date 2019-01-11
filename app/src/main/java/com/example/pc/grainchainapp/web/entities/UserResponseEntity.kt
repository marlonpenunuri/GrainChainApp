package com.example.pc.grainchainapp.web.entities

import com.google.gson.annotations.SerializedName

data class UserResponseEntity(
    @SerializedName("statusCode") var statusCode: String,
    @SerializedName("body") var body: BodyObject
) {

    data class BodyObject(
        @SerializedName("status") var status: String,
        @SerializedName("auth") var auth: AuthObject
    )

    data class AuthObject(
        @SerializedName("user") var user: UserObject,
        @SerializedName("access_token") var access_token: String
    )

    data class UserObject(
        @SerializedName("name") var name: String,
        @SerializedName("lastname") var lastname: String,
        @SerializedName("email") var email: String,
        @SerializedName("address") var address: String
    )
}



