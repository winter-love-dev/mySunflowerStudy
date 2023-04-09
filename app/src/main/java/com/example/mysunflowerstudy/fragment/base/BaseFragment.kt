package com.example.mysunflowerstudy.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mysunflowerstudy.databinding.FragmentBaseBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment2<T: ViewDataBinding>: Fragment() {

    val TAG by lazy { this::class.simpleName.toString() }

    abstract val layoutResourceId: Int

    private var viewDataBinding: T? = null
    val binding
        get() = viewDataBinding!!

    lateinit var bindingBase: FragmentBaseBinding

    protected val baseCoroutine by lazy { CoroutineScope(Job() + Dispatchers.Main) }

    protected open fun initStartView() {}
    protected abstract fun initAfterView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentBaseBinding.inflate(inflater, container, false).run {
            bindingBase = this
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, baseLayout, true)
            this.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartView()
        initAfterView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding = null
    }

    protected fun destroyRootBinding() {
        viewDataBinding = null
    }

}