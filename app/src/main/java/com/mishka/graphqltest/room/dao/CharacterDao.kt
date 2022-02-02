package com.mishka.graphqltest.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.mishka.graphqltest.room.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = REPLACE)
    fun insertCharacter(characterEntity: CharacterEntity): Long

    @Update
    fun updateCharacter(characterEntity: CharacterEntity)

    @Query("SELECT * FROM character")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Int): CharacterEntity

}