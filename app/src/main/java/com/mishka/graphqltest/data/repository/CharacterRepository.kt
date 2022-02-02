package com.mishka.graphqltest.data.repository

import com.mishka.graphqltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacterSingle(characterId: Int): CharacterModel

    suspend fun saveCharacterSingle(model: CharacterModel)

    suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel

    suspend fun updateCharacter(characterModel: CharacterModel)

    fun getCharactersRemote(): Flow<List<CharacterModel>>

}