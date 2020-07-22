package com.android.sapo.ui.general

import android.os.Bundle
import com.android.sapo.R
import com.android.sapo.data.model.User
import com.android.sapo.ui.Sex
import com.android.sapo.ui.overall.OverallFragment
import com.android.sapo.ui.register.RegisterFragment
import com.core.BaseFragment
import com.event.EventNextFragment
import com.google.gson.Gson
import com.utils.ext.argument
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_general.*
import org.koin.android.ext.android.inject

class GeneralFragment : BaseFragment() {

    private val gson by inject<Gson>()
    private val u by argument(RegisterFragment.USER, "")
    private var user: User? = null

    override fun getLayoutId(): Int = R.layout.fragment_general

    override fun updateUI(savedInstanceState: Bundle?) {
        if (u.isNotEmpty()) {
            user = gson.fromJson(u, User::class.java)
        }

        toolbarGeneral.setNavigationOnClickListener { onBackPressed() }

        btnNextGeneral.setOnClickListener {
            when (radGroup.checkedRadioButtonId) {
                R.id.radFemale -> {
                    user?.sex = Sex.FEMALE
                }
                R.id.radMale -> {
                    user?.sex = Sex.MALE
                }
                else -> {
                    user?.sex = Sex.OTHER
                }
            }
            user?.age = pickerAge.value

            val bundle = Bundle().apply {
                putString(RegisterFragment.USER, gson.toJson(user))
            }
            postNormal(EventNextFragment(OverallFragment::class.java, bundle, true))
        }

        pickerAge.minValue = 8
        pickerAge.maxValue = 80
        pickerAge.value = 25
    }
}
