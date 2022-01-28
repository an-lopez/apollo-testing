package com.mishka.graphqltest.data.repository

import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterRepository : CharacterRepository {

    private val characters = mutableListOf<CharacterModel>(
        CharacterModel(
            0,
            "Pedro",
            OriginModel(0, "", "", ""),
            "0",
            "0",
            emptyList()
        ),
        CharacterModel(
            1,
            "Juan",
            OriginModel(0, "", "", ""),
            "0",
            "0",
            emptyList()
        )
    )

    override suspend fun getCharacterSingle(characterId: Int): CharacterModel {
        return characters.find { it.id == characterId }!!
    }

    override suspend fun saveCharacterSingle(model: CharacterModel) {
        characters.add(model)
    }

    override suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel {
        return characters.find { it.id == characterId }!!
    }

    override suspend fun updateCharacter(characterModel: CharacterModel) {
        characters.withIndex().firstOrNull{ it.value.id == characterModel.id}?.let{
            characters.set(it.index, characterModel)
        }
    }

    override fun getCharactersRemote(): Flow<List<CharacterModel>> {
        return flow { emit(characters)}
    }


}