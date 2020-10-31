package com.jefpoughon.animals.ui

import androidx.appcompat.app.AppCompatActivity
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.ui.settings.AppSettings
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseActivity : KoinComponent, AppCompatActivity()  {

    val settings: AppSettings by inject()

    fun manageFavorite(picture: AnimalPicture) {
        //save it in room
    }
}