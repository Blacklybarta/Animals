package com.jefpoughon.animals.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.jefpoughon.animals.db.*
import com.jefpoughon.animals.ui.settings.AppSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule by lazy {
    module {
        // --- Android ---
        single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }

        //settings
        single { AppSettings() }

        //DataBase
        single {
            Room.databaseBuilder(androidContext(), AppDataBase::class.java, "db")
                .build()
        }
        single<AnimalDbDao> { AnimalDbDaoDecorated(get<AppDataBase>().animalDbDao()) }
    }
}
