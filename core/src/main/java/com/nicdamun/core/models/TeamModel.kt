package com.nicdamun.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel (
    val id: String,
    val name: String,
    val stadiumName: String,
    val badge: String,
    val banner: String,
    val description: String,
    val formedYear: Int,
    val jersey: String,
    val website: String?,
    val facebook: String?,
    val twitter: String?,
    val instagram: String?
): Parcelable