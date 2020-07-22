package com.example.kotlinapp.ui.district

import android.os.Bundle
import android.view.View
import com.core.BaseFragment
import com.core.OnItemClick
import com.event.EventNextFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.district.adapter.DistrictAdapter
import com.example.kotlinapp.ui.general.GeneralFragment
import com.example.kotlinapp.ui.register.FragmentRegister.Companion.USER
import com.google.gson.Gson
import com.utils.ext.argument
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_district.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DistrictFragment : BaseFragment() {

    private val viewModel by viewModel<DistrictViewModel>()
    private val gson by inject<Gson>()
    private val districtAdapter by lazy { DistrictAdapter() }
    private val u by argument(USER, "")
    private var user: User? = null

    override fun getLayoutId() = R.layout.fragment_district

    override fun updateUI(savedInstanceState: Bundle?) {
        setUpRcv(rcvDistrict, districtAdapter)
        if (u.isNotEmpty()) {
            user = gson.fromJson(u, User::class.java)
        }

        toolbarDistrict.setNavigationOnClickListener { onBackPressed() }

        districtAdapter.onItemClick = object : OnItemClick {
            override fun onItemClickListener(view: View, position: Int) {
                user?.district = districtAdapter.getDistrict(position)

                val bundle = Bundle().apply { putString(USER, gson.toJson(user)) }

                postNormal(EventNextFragment(GeneralFragment::class.java, bundle, true))
            }

        }

        addDispose(
            viewModel.output.isLoading.subscribe {
                if (it) showDialog() else hideDialog()
            }, viewModel.output.district.subscribe {
                districtAdapter.items = it
            }, viewModel.getDistrict(user?.city)
        )
    }
}