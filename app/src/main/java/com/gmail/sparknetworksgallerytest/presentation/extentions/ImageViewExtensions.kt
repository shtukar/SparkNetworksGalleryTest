package com.gmail.sparknetworksgallerytest.presentation.extentions

import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url: String?, placeHolder: Int? = null) {

    var request = Glide.with(context)
            .load(url)
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))

    placeHolder?.let {
        request = request.placeholder(placeHolder)
    }

    request.into(this)
}