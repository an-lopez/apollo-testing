package com.mishka.graphqltest.interactors.dashboard

import com.mishka.graphqltest.domain.dashboard.CharacterDetailRepository
import com.mishka.graphqltest.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class CharacterDetailViewState(
    val characters: CharacterModel? = null,
    val loading: Boolean = false,
    val error: Throwable? = null
)

class GetSingleCharacterUseCase @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) {

    fun invoke(characterId: Int): Flow<CharacterDetailViewState> = flow {
        emit(CharacterDetailViewState(loading = true))
        val characters = characterDetailRepository.getCharacterSingle(characterId)
        emit(CharacterDetailViewState(characters = characters))
    }

}