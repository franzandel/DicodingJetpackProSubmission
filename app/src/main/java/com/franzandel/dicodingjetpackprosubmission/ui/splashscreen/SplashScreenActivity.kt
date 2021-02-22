package com.franzandel.dicodingjetpackprosubmission.ui.splashscreen

import android.content.Intent
import android.os.Handler
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseActivity
import com.franzandel.dicodingjetpackprosubmission.ui.dashboard.DashboardActivity

class SplashScreenActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_splash_screen

    override fun onActivityCreated() {
        delayOneSecond()
    }

    private fun delayOneSecond() {
        Handler().postDelayed({
            kotlin.run {
                navigateToDashboard()
                finish()
            }
        }, 1000L)
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}