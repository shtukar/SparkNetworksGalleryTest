package com.gmail.sparknetworksgallerytest.presentation.extentions

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(makeInvisible: Boolean = false) {
    this.visibility = if (makeInvisible) View.INVISIBLE else View.GONE
}
