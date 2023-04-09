package com.example.mysunflowerstudy.fragment

import androidx.appcompat.app.AppCompatActivity
import com.example.mysunflowerstudy.R
import com.example.mysunflowerstudy.adapter.MY_GARDEN_PAGE_INDEX
import com.example.mysunflowerstudy.adapter.PLANT_LIST_PAGE_INDEX
import com.example.mysunflowerstudy.adapter.SunflowerPagerAdapter
import com.example.mysunflowerstudy.fragment.base.BaseFragment2
import com.example.mysunflowerstudy.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment: BaseFragment2<FragmentViewPagerBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_view_pager

    override fun initAfterView() {
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SunflowerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> null
        }
    }
}