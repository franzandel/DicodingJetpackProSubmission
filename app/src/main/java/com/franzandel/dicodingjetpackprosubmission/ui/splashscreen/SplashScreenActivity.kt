package com.franzandel.dicodingjetpackprosubmission.ui.splashscreen

import android.os.Handler
import android.os.Looper
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseActivity
import com.franzandel.dicodingjetpackprosubmission.external.extension.goTo
import com.franzandel.dicodingjetpackprosubmission.instrumentedtest.EspressoIdlingResource
import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity

class SplashScreenActivity : BaseActivity() {

    companion object {
        private const val TIME_OUT = 1000L
    }

    override fun getLayoutId(): Int = R.layout.activity_splash_screen

    override fun onActivityCreated() {
        delayOneSecond()
    }

    private fun delayOneSecond() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            kotlin.run {
                goTo(DashboardActivity::class.java)
                finish()
                EspressoIdlingResource.decrement()
            }
        }, TIME_OUT)
    }
}