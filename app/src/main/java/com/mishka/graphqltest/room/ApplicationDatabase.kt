package com.mishka.graphqltest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mishka.graphqltest.room.dao.CharacterDao
import com.mishka.graphqltest.room.model.CharacterEntity
import com.mishka.graphqltest.room.model.OriginEntity

@Database(entities = [CharacterEntity::class, OriginEntity::class], version = 3)
abstract class ApplicationDatabase : RoomDatabase(){

    abstract fun characterDao(): CharacterDao

}