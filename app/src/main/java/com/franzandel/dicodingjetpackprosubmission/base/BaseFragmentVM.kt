package com.franzandel.dicodingjetpackprosubmission.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.franzandel.dicodingjetpackprosubmission.ui.loading.LoadingDialog
import java.lang.ref.WeakReference

/**
 * Created by Franz Andel on 14/03/21.
 * Android Engineer
 */

abstract class BaseFragmentVM<VM : ViewModel, VB : ViewBinding> : BaseFragment<VB>() {

    private val loadingDialog = WeakReference(LoadingDialog.newInstance())

    abstract fun getVM(): VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver(getVM() as BaseViewModel)
    }

    private fun setupObserver(viewModel: BaseViewModel) {
        viewModel.loadingResult.observe(viewLifecycleOwner, Observer {
            if (it)
                loadingDialog.get()?.show(requireActivity().supportFragmentManager)
            else
                loadingDialog.get()?.hide()
        })
    }
}