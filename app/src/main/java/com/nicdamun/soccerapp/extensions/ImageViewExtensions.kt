package com.nicdamun.soccerapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?, cornerRadius: Int = 1) {
    val requestOptions = RequestOptions().apply { transform(RoundedCorners(cornerRadius)) }
    Glide.with(this.context)
        .load(url.orEmpty())
        .apply(requestOptions)
        .into(this)
}