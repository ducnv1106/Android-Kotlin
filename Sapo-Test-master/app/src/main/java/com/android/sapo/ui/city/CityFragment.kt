package com.android.sapo.ui.city

import android.os.Bundle
import android.view.View
import com.android.sapo.R
import com.android.sapo.data.model.User
import com.android.sapo.ui.city.adapter.CityAdapter
import com.android.sapo.ui.district.DistrictFragment
import com.android.sapo.ui.register.RegisterFragment.Companion.USER
import com.core.BaseFragment
import com.core.OnItemClick
import com.event.EventNextFragment
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

    override fun getLayoutId(): Int = R.layout.fragment_city

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
                },
                viewModel.output.city.subscribe {
                    cityAdapter.items = it
                },
                viewModel.getCity()
        )
    }
}
