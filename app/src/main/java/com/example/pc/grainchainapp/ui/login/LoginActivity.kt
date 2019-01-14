package com.example.pc.grainchainapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.example.pc.grainchainapp.R
import com.example.pc.grainchainapp.ui.main.MainActivity
import com.example.pc.grainchainapp.web.entities.UserLoginEntity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(), LoginContract.View  {

    private lateinit var mPresenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter = LoginPresenter(this, this)
        initializeListeners()
    }

    override fun obtainLoginInfo(): UserLoginEntity{
        return UserLoginEntity(username = login_email_edit_text.text.toString(), password = login_password_edit_text.text.toString())
//        harvx_190878
//        supersecretpassword
    }

    override fun loginSuccessful(userName : String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user_name", userName)
        startActivity(intent)
        finish()
    }

    override fun loginErrorDialog(){
        toggleLoading()
        MaterialDialog.Builder(this)
            .title(getString(R.string.app_name))
            .titleColor(ContextCompat.getColor(this,R.color.colorPrimary))
            .content(getString(R.string.dialog_login_error))
            .positiveText(getString(R.string.accept))
            .positiveColor(ContextCompat.getColor(this,R.color.colorPrimary))
            .show()
    }

    override fun toggleLoading(){
        if(progress_bar.visibility == View.INVISIBLE){
            progress_bar.visibility = View.VISIBLE
            login_form_layout.visibility = View.INVISIBLE
        } else {
            progress_bar.visibility = View.INVISIBLE
            login_form_layout.visibility = View.VISIBLE
        }
    }

    private fun initializeListeners(){
        login_button.setOnClickListener {
            toggleLoading()
            mPresenter.userLogin()
        }
    }
}