package com.mishka.graphqltest.data.repository

import com.mishka.graphqltest.domain.dashboard.CharacterDetailRepository
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel

class FakeCharacterDetailRepository : CharacterDetailRepository {

    override suspend fun getCharacterSingle(characterId: Int): CharacterModel {
        return CharacterModel(
            1,
            "Rick Martinez",
            OriginModel(
                1,
                "DSL",
                "Dimension",
                "TEST"
            ),
            "Male",
            "test_image",
            emptyList()
        )
    }

    override suspend fun saveCharacterSingle(model: CharacterModel): Int {
        return 0
    }

    override suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel {
        return CharacterModel(
            0,
            "",
            OriginModel(
                0,
                "",
                "",
                ""
            ),
            "0",
            "0",
            emptyList()
        )
    }


}