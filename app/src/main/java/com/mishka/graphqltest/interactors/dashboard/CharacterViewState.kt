package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.domain.model.CharacterModel

data class CharacterViewState(
    val characters: List<CharacterModel>? = null,
    val loading: Boolean = false,
    val error: Throwable? = null
)