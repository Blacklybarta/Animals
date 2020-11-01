package com.jefpoughon.animals.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.jefpoughon.animals.db.AnimalDbDao
import com.jefpoughon.animals.model.AnimalDb
import com.jefpoughon.animals.model.AnimalPicture
import com.jefpoughon.animals.ui.settings.AppSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseActivity : KoinComponent, AppCompatActivity() {

    val settings: AppSettings by inject()
    private val animalDao: AnimalDbDao by inject()

    fun manageFavorite(picture: AnimalPicture) {

        GlobalScope.launch(Dispatchers.IO) {
            val favorite = AnimalDb(Uri.parse(picture.filePath))
            if (picture.favorite) {
                animalDao.insertAll(favorite)
            } else {
                animalDao.delete(favorite)
            }
        }
    }
}