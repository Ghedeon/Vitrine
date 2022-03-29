package com.ghedeon.vitrine.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghedeon.vitrine.data.db.model.PersonaEntity

@Dao
interface PersonasDao {

    @Query("SELECT * FROM personas_table")
    fun getPersonas(): PagingSource<Int, PersonaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonas(personas: List<PersonaEntity>)

    @Query("DELETE FROM personas_table")
    suspend fun deleteAll()
}