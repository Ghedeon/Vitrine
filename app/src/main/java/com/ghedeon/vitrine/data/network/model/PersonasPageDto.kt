package com.ghedeon.vitrine.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonasPageDto(
    @SerialName("info")
    val info: InfoDto,
    @SerialName("results")
    val results: List<PersonaDto>
)

@Serializable
data class InfoDto(
    @SerialName("count")
    val count: Int,
    @SerialName("pages")
    val pages: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("prev")
    val prev: String?
)