package com.example.mysunflowerstudy.fragment

import com.example.mysunflowerstudy.R
import com.example.mysunflowerstudy.databinding.FragmentGardenBinding
import com.example.mysunflowerstudy.fragment.base.BaseFragment2

class GardenFragment: BaseFragment2<FragmentGardenBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_garden

    override fun initAfterView() {

    }
}