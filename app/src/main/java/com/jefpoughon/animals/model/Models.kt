package com.jefpoughon.animals.model

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.net.URI

data class AnimalPicture(
    val filePath: String,
    var image: Bitmap?,
    var favorite: Boolean
)

@Entity
data class AnimalDb(
    @PrimaryKey
    val uri: Uri
)

data class AnimalJson(
    val file: String = "",
    val url: String = ""
)
