package com.franzandel.dicodingjetpackprosubmission.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Franz Andel on 22/02/21.
 * Android Engineer
 */

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onActivityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onActivityCreated()
    }
}