package com.franzandel.dicodingjetpackprosubmission.external.extension

import android.view.View

/**
 * Created by Franz Andel on 14/03/21.
 * Android Engineer
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}