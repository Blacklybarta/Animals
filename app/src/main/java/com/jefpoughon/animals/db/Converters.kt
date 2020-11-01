package com.jefpoughon.animals.db

import android.net.Uri
import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromUriToString(value: Uri) = value.toString()

    @TypeConverter
    fun fromStringToUri(value: String): Uri = Uri.parse(value)
}
