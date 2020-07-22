package com.example.kotlinapp.ui.city

import android.os.Bundle
import android.view.View
import com.core.BaseFragment
import com.core.OnItemClick
import com.event.EventNextFragment
import com.example.kotlinapp.R
import com.example.kotlinapp.data.model.User
import com.example.kotlinapp.ui.city.adapter.CityAdapter
import com.example.kotlinapp.ui.district.DistrictFragment
import com.example.kotlinapp.ui.register.FragmentRegister.Companion.USER
import com.google.gson.Gson
import com.utils.ext.argument
import com.utils.ext.postNormal
import kotlinx.android.synthetic.main.fragment_city.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CityFragment : BaseFragment() {

    private val viewModel by viewModel<CityViewModel>()
    private val gson by inject<Gson>()
    private val cityAdapter by lazy { CityAdapter() }
    private val u by argument(USER, "")
    private var user: User? = null

    override fun getLayoutId() = R.layout.fragment_city

    override fun updateUI(savedInstanceState: Bundle?) {

        setUpRcv(rcvCity, cityAdapter)
        if (u.isNotEmpty()) {
            user = gson.fromJson(u, User::class.java)

        }

        cityAdapter.onItemClick = object : OnItemClick {

            override fun onItemClickListener(view: View, position: Int) {
                user?.city = cityAdapter.getCity(position)
                val bundle = Bundle().apply {
                    putString(USER, gson.toJson(user))
                }

                postNormal(EventNextFragment(DistrictFragment::class.java, bundle, true))
            }

        }

        addDispose(
            viewModel.output.isLoading.subscribe {
                if (it) showDialog() else hideDialog()
            }, viewModel.output.city.subscribe {
                cityAdapter.items = it
            }, viewModel.getCity()
        )


    }
}