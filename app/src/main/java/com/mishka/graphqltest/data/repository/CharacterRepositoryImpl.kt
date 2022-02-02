package com.mishka.graphqltest.data.repository

import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import com.mishka.graphqltest.room.dao.CharacterDao
import com.mishka.graphqltest.util.CharacterMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterMapper: CharacterMapper
) : CharacterRepository {

    override suspend fun getCharacterSingle(characterId: Int): CharacterModel {
        return characterMapper.characterEntityToCharacterModel(characterDao.getCharacterById(characterId))
    }

    override suspend fun saveCharacterSingle(model: CharacterModel) {
        val character = characterMapper.characterModelToCharacterEntity(model)
        characterDao.insertCharacter(character)
    }

    override suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel {
        return CharacterModel(0, "", OriginModel(0, "", "", ""), "0", "0", emptyList())
    }

    override suspend fun updateCharacter(characterModel: CharacterModel) {
        characterDao.updateCharacter(characterMapper.characterModelToCharacterEntity(characterModel))
    }

    override fun getCharactersRemote(): Flow<List<CharacterModel>> {
        return characterDao.getCharacters().map { characterMapper.characterEntityListToCharacterModelList(it) }
    }

}