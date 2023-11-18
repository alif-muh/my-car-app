package com.example.mycarapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    val name: String,
    val description: String,
    val specification: String,
    val photo: Int

) : Parcelable
