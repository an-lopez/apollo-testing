package com.mishka.graphqltest.domain.dashboard

import com.mishka.graphqltest.domain.model.CharacterModel

interface CharacterDetailRepository {

    suspend fun getCharacterSingle(characterId: Int): CharacterModel

    suspend fun saveCharacterSingle(model: CharacterModel): Int

    suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel

}