package com.pdmcourse2026.basictemplate.data.database.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pdmcourse2026.basictemplate.model.Place

@Entity(tableName = "options")
data class OptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
)

// Mappers en el mismo archivo
fun OptionEntity.toModel(): Place {
    return Place(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = 0
    )
}

fun Place.toEntity(): OptionEntity {
    return OptionEntity(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}