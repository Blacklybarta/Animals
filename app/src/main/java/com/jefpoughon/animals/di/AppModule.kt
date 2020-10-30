package com.jefpoughon.animals.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.jefpoughon.animals.service.CatService
import com.jefpoughon.animals.ui.settings.AppSettings
import org.koin.dsl.module


val appModule by lazy {
    module {
        // --- Android ---
        single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }

        //settings
        single { AppSettings() }

        //
        single { CatService.Creator.create() }
    }
}
