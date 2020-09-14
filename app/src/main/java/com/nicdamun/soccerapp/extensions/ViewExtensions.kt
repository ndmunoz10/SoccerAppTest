package com.nicdamun.soccerapp.extensions

import android.view.View

fun View.showOrHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}