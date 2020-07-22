package com.example.kotlinapp.ui.welcome

import android.os.Bundle
import com.core.BaseFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.register.FragmentRegister.Companion.USER
import com.google.gson.Gson
import com.utils.ext.argument
import kotlinx.android.synthetic.main.fragment_overall.*
import org.koin.android.ext.android.inject

class WelComeFragment : BaseFragment() {


    override fun getLayoutId(): Int = R.layout.fragment_welcome

    override fun updateUI(savedInstanceState: Bundle?) {



    }
}