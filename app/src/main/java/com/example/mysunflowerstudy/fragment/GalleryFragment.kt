package com.example.mysunflowerstudy.fragment

import com.example.mysunflowerstudy.R
import com.example.mysunflowerstudy.fragment.base.BaseFragment2
import com.example.mysunflowerstudy.databinding.FragmentGalleryBinding

class GalleryFragment: BaseFragment2<FragmentGalleryBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_gallery

    override fun initAfterView() {

    }
}