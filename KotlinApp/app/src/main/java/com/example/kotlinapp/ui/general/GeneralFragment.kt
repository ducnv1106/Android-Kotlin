package com.example.kotlinapp.ui.general

import android.os.Bundle
import com.core.BaseFragment
import com.event.EventNextFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.Sex
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.district.DistrictViewModel
import com.example.kotlinapp.ui.overall.OverallFragment
import com.example.kotlinapp.ui.register.FragmentRegister
import com.google.gson.Gson
import com.utils.ext.argument
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_general.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class GeneralFragment : BaseFragment() {

    private val gson by inject<Gson>()
    private val u by argument(FragmentRegister.USER, "")
    private var user: User? = null

    override fun getLayoutId(): Int = R.layout.fragment_general

    override fun updateUI(savedInstanceState: Bundle?) {

        if(u.isNotEmpty()){
            user = gson.fromJson(u,User::class.java)
        }

        toolbarGeneral.setNavigationOnClickListener{
            onBackPressed()
        }

        btnNextGeneral.setOnClickListener{
            when(radGroup.checkedRadioButtonId){
                R.id.radFemale -> {
                    user?.sex = Sex.FEMALE
                }
                R.id.radMale ->{
                    user?.sex = Sex.MALE
                }
                else -> {
                    user?.sex = Sex.OTHER
                }
            }

        }

        val bundle = Bundle().apply { putString(FragmentRegister.USER,gson.toJson(user)) }

        postNormal(EventNextFragment(OverallFragment::class.java,bundle,true))

    }
}