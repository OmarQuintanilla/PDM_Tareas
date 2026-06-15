package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.model.Place
import kotlinx.coroutines.flow.Flow

interface RankeUcaRepository {
    fun getOptionsLocally(): Flow<List<Place>>
    suspend fun addOption(place: Place)
    suspend fun deleteOption(place: Place)
}