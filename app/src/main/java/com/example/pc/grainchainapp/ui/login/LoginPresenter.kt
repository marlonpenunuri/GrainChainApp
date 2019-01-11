package com.example.pc.grainchainapp.ui.login

import android.content.Context
import com.example.pc.grainchainapp.web.service.AwsServiceGenerator
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter(val mContext: Context, val mView: LoginContract.View) : LoginContract.Presenter {


    override fun userLogin() {
        AwsServiceGenerator
            .createUserService
            .loginUser(mView.obtainLoginInfo())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val user = it.body.auth.user
                mView.loginSuccessful(user.name + " " + user.lastname)
            }, {
               mView.loginErrorDialog()
            })
    }


}