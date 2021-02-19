package com.franzandel.dicodingjetpackprosubmission.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.franzandel.dicodingjetpackprosubmission.DashboardActivity
import com.franzandel.dicodingjetpackprosubmission.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
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