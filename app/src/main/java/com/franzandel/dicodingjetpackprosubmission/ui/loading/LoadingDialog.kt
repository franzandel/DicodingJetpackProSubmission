package com.franzandel.dicodingjetpackprosubmission.ui.loading

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.franzandel.dicodingjetpackprosubmission.R

class LoadingDialog : DialogFragment() {

    companion object {
        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }

        private const val TAG = "LoadingDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.df_loading, container)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupDialogUI()
    }

    private fun setupDialogUI() {
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun show(fragmentManager: FragmentManager) {
        if (!isAdded)
            showNow(fragmentManager, TAG)
    }

    fun hide() {
        if (isAdded || isVisible)
            dismiss()
    }
}
