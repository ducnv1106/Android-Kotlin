package com.example.kotlinapp.ui.overall

import android.os.Bundle
import com.core.BaseFragment
import com.event.EventNextFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.register.FragmentRegister.Companion.USER
import com.example.kotlinapp.ui.welcome.WelComeFragment
import com.google.gson.Gson
import com.utils.ext.argument
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_overall.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class OverallFragment : BaseFragment() {

    private val gson by inject<Gson>()
    private val u by argument(USER, "")
    private var user: User? = null

    override fun getLayoutId(): Int = R.layout.fragment_overall

    override fun updateUI(savedInstanceState: Bundle?) {
        if (u.isNotEmpty()) {
            user = gson.fromJson(u, User::class.java)

        }
        toolbarOverall.setNavigationOnClickListener { onBackPressed() }

        tvName.text = user?.username
        tvEmail.text = user?.email
        tvSex.text = user?.sex?.value
        tvAge.text = user?.age.toString()
        tvCity.text = user?.city?.name
        tvDistrict.text = user?.district?.name

        btnNextWelcome.setOnClickListener {
            postNormal(EventNextFragment(WelComeFragment::class.java, arguments!!, true))
        }
    }
}