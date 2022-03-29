package com.ghedeon.vitrine.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ghedeon.vitrine.data.db.model.RemoteKeyEntity

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys_table WHERE id = :id")
    suspend fun getRemoteKey(id: Int): RemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(keys: List<RemoteKeyEntity>)

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAll()
}