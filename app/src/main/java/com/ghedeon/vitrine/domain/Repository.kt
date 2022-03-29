package com.ghedeon.vitrine.domain

import androidx.paging.PagingData
import com.ghedeon.vitrine.domain.model.Persona
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPersonas(): Flow<PagingData<Persona>>
}