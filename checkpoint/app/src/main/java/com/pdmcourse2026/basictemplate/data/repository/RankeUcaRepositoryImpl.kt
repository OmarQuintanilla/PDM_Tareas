package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.database.dao.OptionDao
import com.pdmcourse2026.basictemplate.data.database.entities.toEntity
import com.pdmcourse2026.basictemplate.data.database.entities.toModel
import com.pdmcourse2026.basictemplate.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RankeUcaRepositoryImpl(
    private val optionDao: OptionDao
) : RankeUcaRepository {

    override fun getOptionsLocally(): Flow<List<Place>> {
        return optionDao.getAllOptions().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun addOption(place: Place) {
        optionDao.insertOption(place.toEntity())
    }

    override suspend fun deleteOption(place: Place) {
        optionDao.deleteOption(place.toEntity())
    }
}