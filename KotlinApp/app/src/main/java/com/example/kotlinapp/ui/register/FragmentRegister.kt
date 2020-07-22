package com.example.kotlinapp.ui.register

import android.os.Bundle
import android.util.Log
import com.core.BaseFragment
import com.event.EventNextFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.city.CityFragment
import com.google.gson.Gson
import com.utils.StringUtils
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

class FragmentRegister : BaseFragment() {

    companion object {
        const val USER = "USER"
    }

    private val gson by inject<Gson>()
    private val user by lazy { User() }

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun updateUI(savedInstanceState: Bundle?) {
        btnNext.setOnClickListener {
            checkErr()
        }
    }

    private fun checkErr() {
        val username = edtUsername.text.toString();
        val email = edtEmail.text.toString();
        val password = edtPassword.text.toString()
        val rePassword = edtRepeatPass.text.toString()

        var isVail = true

        if (username.length < 8) {
            isVail = false
            edtUsername.error = "Username > 8 ki tu"

        }
        if (!StringUtils.isValidEmail(email)) {

            edtEmail.error = "Email khong hop le"
            isVail = false
        }

        val isAtleast8: Boolean = password.length > 8
        val hasSpecial: Boolean = !password.matches(Regex("[A-Za-z0-9 ]*"))
        val hasUppercase = password != password.toLowerCase()
        val hasLowercase = password != password.toUpperCase()

        if (!isAtleast8 || !hasSpecial || !hasLowercase || !hasUppercase) {
            edtPassword.error = "Password > 8 ky tu. Co chu cai hoa, chu thuong va ky tu dac biet."
//            toast(edtPassword.error)
            return
        }
        if (password != rePassword) {
            edtPassword.error = "Repeat password phải trùng với password"
            return
        }

        if (isVail) {
            user.email = email
            user.username = username
            user.password = password
            Log.e("Rigister", email)
            Log.e("Rigister", username)
            Log.e("Rigister", password)
//            val bundle = Bundle().apply {
//                putString(USER, gson.toJson(user))
//            }

            postNormal(EventNextFragment(CityFragment::class.java, true))
        }
    }

}