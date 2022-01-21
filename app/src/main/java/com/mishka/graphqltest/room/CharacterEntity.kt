package com.mishka.graphqltest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val gender: String,
    val image: String
)