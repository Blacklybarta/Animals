package com.jefpoughon.animals.ui.settings

import android.content.SharedPreferences
import org.koin.core.KoinComponent
import org.koin.core.inject

private const val KEY_CATS = "key_cats"
private const val KEY_DOGS = "key_dogs"
private const val KEY_FOXES = "key_foxes"
private const val KEY_NUMBER = "key_number"

private const val NUMBER_DEFAULT = "10"


class AppSettings : KoinComponent {

    private val pref: SharedPreferences by inject()

    val isCats: Boolean
        get() = pref.getBoolean(KEY_CATS, true)

    val isDogs: Boolean
        get() = pref.getBoolean(KEY_DOGS, false)

    val isFoxes: Boolean
        get() = pref.getBoolean(KEY_FOXES, false)

    val number: String
        get() = pref.getString(KEY_NUMBER, NUMBER_DEFAULT) ?: NUMBER_DEFAULT

}
