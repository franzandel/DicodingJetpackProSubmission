package com.franzandel.dicodingjetpackprosubmission.external

import android.app.Activity
import androidx.core.app.ShareCompat
import com.franzandel.dicodingjetpackprosubmission.R

/**
 * Created by Franz Andel on 18/02/21.
 * Android Engineer
 */

fun Activity.showShareMessage() {
    val mimeType = "text/plain"
    ShareCompat.IntentBuilder
        .from(this)
        .setType(mimeType)
        .setChooserTitle(getString(R.string.share_message_title))
        .setText(getString(R.string.share_message_description))
        .startChooser()
}