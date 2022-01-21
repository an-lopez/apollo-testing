package com.mishka.graphqltest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origin")
data class OriginEntity(
    @PrimaryKey
    val id: Int,
    val characterId: Int,
    val name: String,
    val dimension: String,
    val type: String
)