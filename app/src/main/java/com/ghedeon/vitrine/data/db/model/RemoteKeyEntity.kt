package com.ghedeon.vitrine.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_table")
data class RemoteKeyEntity(
    @PrimaryKey
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
