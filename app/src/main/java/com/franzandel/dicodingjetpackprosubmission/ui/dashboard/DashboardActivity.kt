package com.franzandel.dicodingjetpackprosubmission.ui.dashboard

import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ShareCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_dashboard

    override fun onActivityCreated() {
        setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_message_title))
                    .setText(getString(R.string.share_message_description))
                    .startChooser()
            }
        }
        return true
    }

    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = findViewById(R.id.navView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }
}