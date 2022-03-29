package com.ghedeon.vitrine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghedeon.vitrine.data.db.model.PersonaEntity
import com.ghedeon.vitrine.data.db.model.RemoteKeyEntity

@Database(entities = [PersonaEntity::class, RemoteKeyEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun personasDao(): PersonasDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}