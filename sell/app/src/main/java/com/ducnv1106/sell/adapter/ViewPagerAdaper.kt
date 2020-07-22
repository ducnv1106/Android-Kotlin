package com.ducnv1106.sell.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


open class ViewPagerAdaper(
    fm: FragmentManager,
    private val listFragment: List<Fragment>,
    private val listTitle: List<String>
) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment = this.listFragment[position]

    override fun getCount(): Int = this.listTitle.size

    override fun getPageTitle(position: Int): CharSequence? = this.listTitle[position]
}