package com.android.sapo.ui.register

import android.os.Bundle
import com.android.sapo.R
import com.android.sapo.data.model.User
import com.android.sapo.ui.city.CityFragment
import com.core.BaseFragment
import com.event.EventNextFragment
import com.google.gson.Gson
import com.utils.StringUtils
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment() {

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
        val username = edtUsername.text.toString()
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        val rePassword = edtRepeatPass.text.toString()

        var isValid = true
        if (username.length < 8) {
            isValid = false
            edtUsername.error = "Username > 8 kí tự"
        }
        if (!StringUtils.isValidEmail(email)) {
            edtEmail.error = "Email không hợp lệ"
            isValid = false
        }

        val isAtLeast8: Boolean = password.length > 8
        val hasSpecial: Boolean = !password.matches(Regex("[A-Za-z0-9 ]*"))
        val hasUppercase = password != password.toLowerCase()
        val hasLowercase = password != password.toUpperCase()

        if (!isAtLeast8 || !hasSpecial || !hasLowercase || !hasUppercase) {
            edtPassword.error = "Password > 8 ký tự. Có chữ hoa, chữ thường, và ký tự đặc biệt."
            return
        }
        if (password != rePassword) {
            edtRepeatPass.error = "Repeat password phải trùng với password"
            return
        }
        if (isValid) {
            user.email = email
            user.username = username
            user.password = password

            val bundle = Bundle().apply {
                putString(USER, gson.toJson(user))
            }
            postNormal(EventNextFragment(CityFragment::class.java, bundle, true))
        }
    }
}
