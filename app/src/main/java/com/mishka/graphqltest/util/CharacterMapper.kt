package com.mishka.graphqltest.util

import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.room.model.CharacterEntity
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    val characterModelToCharacterEntity: (@JvmSuppressWildcards CharacterModel) -> @JvmSuppressWildcards CharacterEntity,
    val characterEntityToCharacterModel: (@JvmSuppressWildcards CharacterEntity) -> @JvmSuppressWildcards CharacterModel,
    val characterEntityListToCharacterModelList: @JvmSuppressWildcards (List<CharacterEntity>) -> @JvmSuppressWildcards List<CharacterModel>
)