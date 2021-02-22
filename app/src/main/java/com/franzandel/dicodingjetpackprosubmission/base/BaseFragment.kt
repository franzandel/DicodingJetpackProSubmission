package com.franzandel.dicodingjetpackprosubmission.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Franz Andel on 22/02/21.
 * Android Engineer
 */

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var viewBinding: VB

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    abstract fun onFragmentCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getViewBinding(inflater, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated()
    }
}