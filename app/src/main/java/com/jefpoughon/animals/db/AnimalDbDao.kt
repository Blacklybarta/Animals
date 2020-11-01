package com.jefpoughon.animals.db

import androidx.room.*
import com.jefpoughon.animals.model.AnimalDb

@Dao
interface AnimalDbDao {

    @Query("SELECT * FROM animalDb")
    fun getAll(): List<AnimalDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg animalDb: AnimalDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(animalDb: List<AnimalDb>)

    @Delete
    fun delete(animalDb: AnimalDb)
}

class AnimalDbDaoDecorated(private val animalDbDao: AnimalDbDao) : AnimalDbDao {

    override fun getAll(): List<AnimalDb> {
        return animalDbDao.getAll()
    }

    override fun insertAll(vararg animalDb: AnimalDb) {
        animalDbDao.insertAll(*animalDb)
    }

    override fun insertAll(animalDb: List<AnimalDb>) {
        animalDbDao.insertAll(animalDb)
    }

    override fun delete(animalDb: AnimalDb) {
        animalDbDao.delete(animalDb)
    }
}
