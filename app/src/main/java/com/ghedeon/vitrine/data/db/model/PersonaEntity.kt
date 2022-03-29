package com.ghedeon.vitrine.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personas_table")
data class PersonaEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String
)