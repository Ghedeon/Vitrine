package com.ghedeon.vitrine.data

import com.ghedeon.vitrine.data.db.model.PersonaEntity
import com.ghedeon.vitrine.data.network.model.PersonaDto
import com.ghedeon.vitrine.domain.model.Persona

fun PersonaDto.toEntity() = PersonaEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    url = url,
    created = created
)

fun PersonaEntity.toDomain() = Persona(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image,
    url = url,
    created = created
)