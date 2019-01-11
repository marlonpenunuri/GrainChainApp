package com.example.pc.grainchainapp.ui.main

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.example.pc.grainchainapp.Application
import com.example.pc.grainchainapp.R
import com.example.pc.grainchainapp.db.ContactEntity
import com.example.pc.grainchainapp.ui.main.contactList.ContactListFragment
import com.example.pc.grainchainapp.ui.main.newContact.NewContactFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mContext: Context
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolbar = findViewById(R.id.toolbar)
        mContext = this

        initializeViewPager()

        val extras = intent.extras
        if (extras != null) {
            setupToolbar(extras.getString("user_name"))
        }

    }

    private fun initializeViewPager() {
        val adapter = MainPagerAdapter(supportFragmentManager)
        adapter.addFragment(ContactListFragment(), getString(R.string.activity_main_tab_1))
        adapter.addFragment(NewContactFragment(), getString(R.string.activity_main_tab_2))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


    private fun setupToolbar(userName: String?) {
        (mContext as AppCompatActivity).setSupportActionBar(mToolbar)
        (mContext as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mToolbar.setTitleTextColor(ContextCompat.getColor(mContext, android.R.color.white))
        (mContext as AppCompatActivity).supportActionBar?.title = userName
    }

}