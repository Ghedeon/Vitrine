@file:OptIn(ExperimentalPagingApi::class)

package com.ghedeon.vitrine.data

import androidx.paging.*
import com.ghedeon.vitrine.data.db.Database
import com.ghedeon.vitrine.data.db.model.PersonaEntity
import com.ghedeon.vitrine.data.network.WebService
import com.ghedeon.vitrine.data.paging.PagingRemoteMediator
import com.ghedeon.vitrine.domain.Repository
import com.ghedeon.vitrine.domain.model.Persona
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: WebService,
    private val database: Database
) : Repository {

    override fun getPersonas(): Flow<PagingData<Persona>> = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = PagingRemoteMediator(service = service, database = database),
        pagingSourceFactory = { database.personasDao().getPersonas() }
    )
        .flow
        .map { data -> data.map(PersonaEntity::toDomain) }
}