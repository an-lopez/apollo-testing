package com.mishka.graphqltest.domain.dashboard

import com.mishka.graphqltest.domain.model.CharacterModel

data class CharacterDetailViewState(
    val characters: CharacterModel? = null,
    val loading: Boolean = false,
    val error: Throwable? = null
)