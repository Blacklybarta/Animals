package com.jefpoughon.animals.model

import android.graphics.Bitmap


data class AnimalPicture(
    val file: String?, //cat
    val url: String?,   //dog
    var image: Bitmap?,
    var favorite : Boolean
)