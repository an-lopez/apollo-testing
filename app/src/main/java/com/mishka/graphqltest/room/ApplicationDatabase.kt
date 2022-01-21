package com.mishka.graphqltest.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class, OriginEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase()