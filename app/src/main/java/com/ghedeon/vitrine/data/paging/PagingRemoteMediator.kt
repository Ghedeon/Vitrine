@file:OptIn(ExperimentalPagingApi::class)

package com.ghedeon.vitrine.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ghedeon.vitrine.data.db.Database
import com.ghedeon.vitrine.data.db.model.PersonaEntity
import com.ghedeon.vitrine.data.db.model.RemoteKeyEntity
import com.ghedeon.vitrine.data.network.WebService
import com.ghedeon.vitrine.data.network.model.PersonaDto
import com.ghedeon.vitrine.data.toEntity
import javax.inject.Inject

class PagingRemoteMediator @Inject constructor(
    private val service: WebService, private val database: Database
) : RemoteMediator<Int, PersonaEntity>() {

    private val personasDao get() = database.personasDao()
    private val remoteKeysDao get() = database.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PersonaEntity>): MediatorResult =
        runCatching {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    remoteKey?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
            }

            val personas = service.getPersonas(page = currentPage)
            val endOfPaginationReached = personas.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    personasDao.deleteAll()
                    remoteKeysDao.deleteAll()
                }
                val keys = personas.results.map { persona ->
                    RemoteKeyEntity(
                        id = persona.id, prevPage = prevPage, nextPage = nextPage
                    )
                }
                remoteKeysDao.addRemoteKeys(keys)
                personasDao.addPersonas(personas.results.map(PersonaDto::toEntity))
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }.getOrElse(MediatorResult::Error)

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PersonaEntity>): RemoteKeyEntity? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id)
            }
        }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PersonaEntity>): RemoteKeyEntity? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { persona ->
            remoteKeysDao.getRemoteKey(id = persona.id)
        }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PersonaEntity>): RemoteKeyEntity? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { persona ->
            remoteKeysDao.getRemoteKey(id = persona.id)
        }
}