package com.jefpoughon.animals.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jefpoughon.animals.model.AnimalDb

@Database(entities = [AnimalDb::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun animalDbDao(): AnimalDbDao
}
