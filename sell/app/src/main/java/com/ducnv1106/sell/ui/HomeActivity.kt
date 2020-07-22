package com.ducnv1106.sell.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.ducnv1106.sell.R
import com.ducnv1106.sell.adapter.NaviAdapter
import com.ducnv1106.sell.adapter.ViewPagerAdaper
import com.ducnv1106.sell.base.BaseFragment
import com.ducnv1106.sell.databinding.ActivityHomeBinding
import com.ducnv1106.sell.model.ProductType
import com.ducnv1106.sell.utils.Constants
import com.ducnv1106.sell.viewmodel.HomeViewModel


class HomeActivity : AppCompatActivity(), HomeNavigator {

    private lateinit var binding: ActivityHomeBinding

    private val dataNavigation = mutableListOf<ProductType>()

    private val adapterNavi by lazy { NaviAdapter(this) }

    private lateinit var viewPagerAdaper: ViewPagerAdaper

    private val fragmentCar by lazy { CarFragment() }

    private val fragmentMotoBike by lazy { MotoBikeFragment() }

    private val fragmentBike by lazy { BikeFragment() }

    private lateinit var homeNavigator: HomeNavigator

    private val model by lazy { HomeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setUpViewModel()
        initNavi()
        initViewPager()
        listenertEdt()
        setUpNavi()
        setNavigator()
        setUpViewFlipper()
    }

    private fun setUpViewModel() {
        model.initAmountOrder()
        model.getAmount().observe(this, Observer {
            if (it == 0) {
                binding.tvAmount.visibility = View.GONE
                return@Observer
            } else binding.tvAmount.visibility = View.VISIBLE
            binding.tvAmount.text = it.toString()

        })
    }

    private fun setNavigator() {
        homeNavigator = this
        binding.listener = homeNavigator

    }

    private fun listenertEdt() {
        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.e("result", s.toString())
                val fm = viewPagerAdaper.getItem(binding.viewPager.currentItem) as BaseFragment<*>
                fm.executeSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun initViewPager() {
        val listFragment = mutableListOf<Fragment>(fragmentCar, fragmentMotoBike, fragmentBike)
        val listTitle = mutableListOf(
            fragmentCar.getTitle(),
            fragmentMotoBike.getTitle(),
            fragmentBike.getTitle()
        )
        viewPagerAdaper = ViewPagerAdaper(supportFragmentManager, listFragment, listTitle)
        binding.viewPager.adapter = viewPagerAdaper
        viewPagerAdaper.getItem(0)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.edtSearch.setText("")
                binding.edtSearch.clearFocus()
            }

        })

    }

    private fun initNavi() {
        dataNavigation.add(ProductType(0, "Phương tiện", null, true))
        dataNavigation.add(ProductType(2, "Ô tô", R.drawable.ic_car, false))
        dataNavigation.add(ProductType(1, "Xe máy", R.drawable.ic_motorcycle, false))
        dataNavigation.add(ProductType(3, "Xe đạp", R.drawable.ic_bicycle, false))
    }

    private fun setUpNavi() {
        binding.listview.adapter = adapterNavi
        adapterNavi.data = dataNavigation
        adapterNavi.listener = object : NaviAdapter.NaviAdaperListener {
            override fun onItemClicked(position: Int) {
                val item = adapterNavi.data[position]
                when (item.id) {
                    Constants.ID_CAR -> {
                        binding.viewPager.currentItem = 0
                        binding.drawerLayout.closeDrawers()
                    }
                    Constants.ID_MOTOBIKE -> {
                        binding.viewPager.currentItem = 1
                        binding.drawerLayout.closeDrawers()
                    }
                    Constants.ID_BIKE -> {
                        binding.viewPager.currentItem = 2
                        binding.drawerLayout.closeDrawers()
                    }


                }
            }

        }
    }

    private fun setUpViewFlipper() {
        binding.viewFlipper.flipInterval = 5000
        binding.viewFlipper.isAutoStart = true
        val slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right)
        val slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        binding.viewFlipper.inAnimation = slide_in_right
        binding.viewFlipper.outAnimation = slide_out_right
    }

    override fun onMenuClicked() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onOrderClicked() {
        val intent = Intent(this, DetailOrderActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }


}